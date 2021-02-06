package com.pet.project.controllers;

import com.pet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;


    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username",name);
        return "home";
    }


}