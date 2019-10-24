package com.example.pty.controller;

import com.example.pty.mode.Publish;
import com.example.pty.mode.User;
import com.example.pty.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    PublishService publishService;

    /**
     * 直接访问
     * @return
     */
    @GetMapping(value = "/publish")
    public String publish(){
        return "/publish";
    }

    /**
     * 携带参数
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/publish/{id}")
    public String publish(@PathVariable(value = "id",required = false)Long id,
                          Model model){
        Publish publish = publishService.selectById(id);
        model.addAttribute("title", publish.getTitle());
        model.addAttribute("description", publish.getDescribeText());
        model.addAttribute("label", publish.getLabel());
        return "/publish";
    }

    /**
     * 提交问题
     * @param title
     * @param describe
     * @param label
     * @return
     */
    @PostMapping(value ="/publish")
    public String publish(@RequestParam (value = "title",required = false) String title,
                          @RequestParam (value = "describe",required = false) String describe,
                          @RequestParam (value = "label",required = false) String label,
                          @RequestParam(value = "id", required = false) Long id,
                          HttpServletRequest request,
                          Model model){
        model.addAttribute("title", title);
        model.addAttribute("description", describe);
        model.addAttribute("label", label);
        if (StringUtils.isEmpty(title)){
            model.addAttribute("error","title 不能为空");
            return "/publish";
        }
        if (StringUtils.isEmpty(describe)){
            model.addAttribute("error","内容 不能为空");
            return "/publish";
        }
        if (StringUtils.isEmpty(label)){
            model.addAttribute("error","标签 不能为空");
            return "/publish";
        }
        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            //用户没有登录
            model.addAttribute("error","用户没有登录");
            return "publish";
        }

        Publish publishMode=new Publish();
        publishMode.setTitle(title);
        publishMode.setDescribeText(describe);
        publishMode.setLabel(label);
        publishMode.setCreatId(user.getId());
        publishMode.setId(id);
        publishService.createOrUpdate(publishMode);

        return "redirect:index";
    }
}
