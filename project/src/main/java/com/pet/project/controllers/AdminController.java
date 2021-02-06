package com.pet.project.controllers;

import com.pet.project.models.Role;
import com.pet.project.models.Status;
import com.pet.project.models.User;
import com.pet.project.repo.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;



@Controller
public class AdminController {

    private final UserRepo userRepo;

    public AdminController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/user-list")
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public String userList(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username",name);
        model.addAttribute("user_list",userRepo.findAll());
        return "user-list";
    }

    @RequestMapping("user-delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public String deleteUser(@PathVariable("id")long id){
        userRepo.deleteById(id);
        return "redirect:/user-list";
    }

   @RequestMapping("/user/{id}/edit")
    @PreAuthorize("hasAuthority('user:edit')")
    public String userEdit(@PathVariable(value = "id")long id,Model model){
        if(!userRepo.existsById(id)){
            return "redirect:/user-list";
        }
        Optional<User> user = userRepo.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user_edit",res);
        return "user-edit";
    }

    @RequestMapping(value = "/user/{id}/edit",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('user:edit')")
    public String userEditPost(@PathVariable(value = "id")long id, @RequestParam String username, @RequestParam Status status, Model model){
        User user = userRepo.findById(id).orElseThrow();
        user.setUsername(username);
        user.setStatus(status);
        userRepo.save(user);
        return "redirect:/user-list";
    }

    @RequestMapping("/user/{id}/change-role")
    @PreAuthorize("hasAuthority('user:change_role')")
    public String userRoleEdit(@PathVariable(value = "id")long id,Model model){
        if(!userRepo.existsById(id)){
            return "redirect:/user-list";
        }
        Optional<User> user = userRepo.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user_change_role",res);
        return "change-role";
    }

    @RequestMapping(value = "/user/{id}/change-role",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('user:change_role')")
    public String userRoleEditPost(@PathVariable(value = "id")long id, Role role, Model model){
        User user = userRepo.findById(id).orElseThrow();
        user.setRole(role);
        userRepo.save(user);
        return "redirect:/user-list";
    }
}
