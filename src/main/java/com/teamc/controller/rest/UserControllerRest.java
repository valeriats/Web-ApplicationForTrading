package com.teamc.controller.rest;

import com.teamc.model.User;
import com.teamc.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@NoArgsConstructor
public class UserControllerRest {

    private UserService userService;

    @Autowired
    public UserControllerRest(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/get/user", produces = "application/json")
    public User getUser() {
        return userService.getAuthUser();
    }
}

