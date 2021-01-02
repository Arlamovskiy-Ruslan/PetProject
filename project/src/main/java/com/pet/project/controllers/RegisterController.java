package com.pet.project.controllers;

import com.pet.project.models.User;
import com.pet.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Controller
public class RegisterController {
    @Autowired
    private UserRepo userRepository;

    @GetMapping("/records")
    public String records(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "records";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "registration";
    }


    @PostMapping("/registration")
    public String registrationPost
            (@RequestParam String first_name
            ,@RequestParam String name
            ,@RequestParam String last_name
            ,@RequestParam String problem
            , Model model) {
        User user = new User(first_name,name,last_name,problem);
        userRepository.save(user);
        return "redirect:/records";
    }



}
