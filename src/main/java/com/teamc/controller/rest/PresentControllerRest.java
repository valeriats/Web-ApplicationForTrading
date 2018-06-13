package com.teamc.controller.rest;


import com.teamc.service.PresentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@NoArgsConstructor
public class PresentControllerRest {

    private PresentService presentService;

    @Autowired
    public PresentControllerRest(PresentService presentService) {
        this.presentService = presentService;
    }

    @GetMapping(value = "/priz/{number}")
    public String numberPriz(@PathVariable("number") int number) {
        return presentService.checkoutPriz(number);
    }
}
