package com.pet.project.controllers;

import com.pet.project.repo.CommentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CommentController {

    private final CommentRepo commentRepo;

    public CommentController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping("/comments")
    public String comments(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username",name);
        model.addAttribute("comments",commentRepo.findAll());
        return "comments";
    }
}
