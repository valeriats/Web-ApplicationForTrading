package com.teamc.controller;

import com.teamc.service.PresentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@NoArgsConstructor
public class PresentController {

    private PresentService presentService;

    @Autowired
    public PresentController(PresentService presentService) {
        this.presentService = presentService;
    }

    @GetMapping(value = "/priz")
    public String prizForm() {
        return "priz";
    }
}
