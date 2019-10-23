package com.example.pty.provide;

import com.alibaba.fastjson.JSON;
import com.example.pty.dto.GitHubOauthDto;
import com.example.pty.dto.GitHubUserDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvide {
    /**
     * 获取token
     * @param gitHubOauthDto
     * @return
     */
    public String getGitHubToken(GitHubOauthDto gitHubOauthDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(gitHubOauthDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String respon=response.body().string();
                String[]split=respon.split("&");
                String[] su=split[0].split("=");
                return su[1];
            } catch (IOException e) {
                e.printStackTrace();
            }
        return "";
    }
    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public GitHubUserDto getGitHubUser(String token){
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+token)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                GitHubUserDto gitHubUserDto=JSON.parseObject(response.body().string(), GitHubUserDto.class);
                return gitHubUserDto;
        } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
}
