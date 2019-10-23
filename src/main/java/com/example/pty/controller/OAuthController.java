package com.example.pty.controller;

import com.example.pty.dto.GitHubOauthDto;
import com.example.pty.dto.GitHubUserDto;
import com.example.pty.provide.GitHubProvide;
import com.example.pty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OAuthController {
    @Autowired
    GitHubProvide gitHubProvide;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    UserService userService;

    @GetMapping("/callback")
    public String oauth(@RequestParam("code") String code,
                        @RequestParam("state") String state,
                        HttpServletResponse response){

        GitHubOauthDto gitHubOauthDto=new GitHubOauthDto();
        gitHubOauthDto.setCode(code);
        gitHubOauthDto.setState(state);
        gitHubOauthDto.setRedirect_uri(redirectUri);
        gitHubOauthDto.setClient_id(clientId);
        gitHubOauthDto.setClient_secret(clientSecret);
        String token=gitHubProvide.getGitHubToken(gitHubOauthDto);
        GitHubUserDto gitHubUser=gitHubProvide.getGitHubUser(token);
        if (gitHubUser!=null){
            //登录成功
            userService.createOrUpdateUser(gitHubUser,response);
            return "redirect:index";
        }else{
            //登录失败
            return "redirect:index";
        }

    }

    /**
     * 完善退出操作
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        //清空session 信息
        request.getSession().removeAttribute("user");
        //清空cookie 信息
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:index";
    }

}
