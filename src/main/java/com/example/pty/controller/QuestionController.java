package com.example.pty.controller;

import com.example.pty.dto.PublishDto;
import com.example.pty.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    PublishService publishService;

    @GetMapping(value = "/question/{id}")
    public String question(@PathVariable(value ="id" ) Long id,
                           Model model){
        PublishDto publish = publishService.getById(id);
        //累加游览次数
        publishService.incView(id);
        model.addAttribute("publishDto",publish);
        return "question";
    }
}
