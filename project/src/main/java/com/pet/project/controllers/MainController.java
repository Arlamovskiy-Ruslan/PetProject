package com.pet.project.controllers;

import com.pet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/")
    public String home(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username",name);
        return "home";
    }


}