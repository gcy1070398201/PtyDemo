package com.example.pty.controller;
import com.example.pty.dto.PubLishListDto;
import com.example.pty.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {



    @Autowired
    PublishService publishService;

    @GetMapping("/index")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "2") Integer size){
        PubLishListDto publishDtoList=publishService.select(page,size);
        model.addAttribute("publishDtoList",publishDtoList);

        return "index";
    }
}
