package com.pet.project.controllers;

import com.pet.project.service.UserRepr;
import com.pet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {

        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user",new UserRepr());
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@Valid UserRepr userRepr, BindingResult bindingResult) {
       if(bindingResult.hasErrors()){
           return "register";
       }
        if (!userRepr.getPassword().equals(userRepr.getRepeatPassword())){
            bindingResult.rejectValue("password","","Паролі не співпадають");
            return "register";
        }
        userService.create(userRepr);
        return "redirect:/login";
    }

}