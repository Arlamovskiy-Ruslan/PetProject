package com.pet.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CommentController {

    @GetMapping("/comments")
    public String comments(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username",name);
        return "comments";
    }
}
