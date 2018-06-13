package com.teamc.controller;

import com.teamc.model.Share;
import com.teamc.model.User;
import com.teamc.service.RoleService;
import com.teamc.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@NoArgsConstructor
public class UserController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public String userForm(Model model) {
        model.addAttribute("user", userService.findAll());
        return "user";
    }

    @GetMapping(value = "/userAdd")
    public String userSubmit(Model model) {
        model.addAttribute("user", new User());
        return "userAdd";
    }

    @PostMapping(value = "/userAdd")
    public String addUser(@ModelAttribute("user") User user) {
        userService.userRegistration(user);
        return "redirect:/user";
    }

    @GetMapping(value = "/editUser/{username}")
    public String userEdit(Model model, @PathVariable("username") String userName) {
        model.addAttribute("user", userService.findByUsername(userName));
        model.addAttribute("role", roleService.findAll());
        return "editUser";
    }

    @PostMapping(value = "/editUser/{username}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam String role, @PathVariable("username") String userName) {
        userService.userUpdate(userName, user, role);
        return "redirect:/user";
    }

    @RequestMapping(value = "/deleteUser/{username}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return "redirect:/user";
    }

    @GetMapping(value = "/userAccount")
    public String userAccount(Model model) {
        return "userAccount";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginPage(Model model) {
        return "login";
    }

}
