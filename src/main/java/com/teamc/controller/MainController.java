package com.teamc.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@NoArgsConstructor
public class MainController {

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/index")
    public String main(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Map<String, Object> model) {
        return "admin";
    }

}