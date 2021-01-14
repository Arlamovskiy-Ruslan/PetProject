package com.pet.project.controllers;

import com.pet.project.models.User;
import com.pet.project.repo.UserRepo;
import com.pet.project.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class AdminController {

    private UserService userService;
    private UserRepo userRepo;

    public AdminController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user-list")
    public String userList(Model model) {
        model.addAttribute("user_list",userRepo.findAll());
        return "user-list";
    }

    @GetMapping("user-delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public String deleteUser(@PathVariable("id")long id){
        userRepo.deleteById(id);
        return "redirect:/user-list";
    }

    @GetMapping("/user/{id}")
    public String userDetails(@PathVariable(value = "id")long id,Model model){
        if(!userRepo.existsById(id)){
            return "redirect:/user-list";
        }
        Optional<User> user = userRepo.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user_page_control",res);
        return "user-page-control";
    }

}
