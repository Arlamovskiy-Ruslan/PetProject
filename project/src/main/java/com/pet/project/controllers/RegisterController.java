package com.pet.project.controllers;

import com.pet.project.models.User;
import com.pet.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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



}
