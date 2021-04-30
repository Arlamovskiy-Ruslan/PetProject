package com.pet.project.controllers;

import com.pet.project.models.*;
import com.pet.project.repo.UserRecordRepo;
import com.pet.project.repo.UserRepo;
import com.pet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Controller
public class AdminController {

    private final UserService userService;

    private final UserRepo userRepo;

    private final UserRecordRepo userRecordRepo;

    public AdminController(UserRepo userRepo,UserService userService,UserRecordRepo userRecordRepo) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.userRecordRepo = userRecordRepo;
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
    public String userEditPost(@PathVariable(value = "id")long id, @Valid User user, Model model){
        userService.editStatusUser(id,user);
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
    public String userRoleEditPost(@PathVariable(value = "id")long id,@Valid User user, Model model){
        userService.editRoleUser(id,user);
        return "redirect:/user-list";
    }

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('user:edit')")
    public ModelAndView downloadExcel(Model model) {

        List<UserRecord> users = userRecordRepo.findAll();
        return new ModelAndView(new ExcelView(), "user_rec", users );
    }
}
