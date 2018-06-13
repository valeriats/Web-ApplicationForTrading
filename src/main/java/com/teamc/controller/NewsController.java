package com.teamc.controller;

import com.teamc.service.NewsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoArgsConstructor
public class NewsController {

    private NewsService service;

    @Autowired
    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping(value = "/news")
    public String userForm(Model model) {
        model.addAttribute("news", service.findAll());
        return "news";
    }

}
