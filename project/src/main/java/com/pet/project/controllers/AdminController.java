package com.pet.project.controllers;

import com.pet.project.repo.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    private UserRepo userRepo;

    public AdminController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/**/user-list")
    public String user_list(Model model) {
        model.addAttribute("user_list",userRepo.findAll());
        return "user-list";
    }

}
