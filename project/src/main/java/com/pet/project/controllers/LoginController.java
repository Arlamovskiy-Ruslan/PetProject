package com.pet.project.controllers;

import com.pet.project.models.User;
import com.pet.project.repo.UserRepo;
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

    private UserRepo userRepo;

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
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@Valid User user, BindingResult bindingResult) {
        userService.create(user);
        return "redirect:/login";
    }


}