package com.pet.project.controllers;

import com.pet.project.service.RecordService;
import com.pet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    RecordService recordService;


    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username",name);
        return "home";
    }


}