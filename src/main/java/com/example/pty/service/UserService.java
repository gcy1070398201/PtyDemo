package com.example.pty.service;

import com.example.pty.dto.GitHubUserDto;
import com.example.pty.mapper.UserMapper;
import com.example.pty.mode.User;
import com.example.pty.mode.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void createOrUpdateUser(GitHubUserDto gitHubUser, HttpServletResponse response) {

        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(gitHubUser.getId());
        List<User> list=userMapper.selectByExample(userExample);
        if (list.size()==0){
            return;
        }
        User dbuser = list.get(0);
        String token= UUID.randomUUID().toString();
        if (dbuser==null){
            User user=new User();
            user.setAccountId(gitHubUser.getId());
            user.setName(gitHubUser.getName());
            user.setToken(token);
            user.setAvatarUrl(gitHubUser.getAvatar_url());
            user.setBio(gitHubUser.getBio());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //更新用户信息
            User updateUser=new User();
            updateUser.setToken(token);
            updateUser.setBio(gitHubUser.getBio());
            updateUser.setName(gitHubUser.getName());
            updateUser.setGmtModified(System.currentTimeMillis());

            UserExample userUpdate=new UserExample();
            userUpdate.createCriteria().andIdEqualTo(dbuser.getId());

            userMapper.updateByExampleSelective(updateUser,userUpdate);
        }
        //添加到Cookie
        response.addCookie(new Cookie("token",token));
    }

    public User findUserId(Long creatId) {

        User user = userMapper.selectByPrimaryKey(creatId);
//        if (user==null){
//
//        }
        return user;
    }

    public User finUserToken(String token) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);
        if (users!=null&&users.size()>0){
            return users.get(0);
        }
        return null;

    }
}
