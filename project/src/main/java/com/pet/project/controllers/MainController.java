package com.pet.project.controllers;

import com.pet.project.models.ExcelView;
import com.pet.project.models.UserRecord;
import com.pet.project.repo.UserRecordRepo;
import com.pet.project.service.RecordService;
import com.pet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserRecordRepo userRecordRepo;

    @Autowired
    public MainController(UserRecordRepo userRecordRepo) {
        this.userRecordRepo = userRecordRepo;

    }

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

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel(Model model) {

        List<UserRecord> users = userRecordRepo.findAll();
        return new ModelAndView(new ExcelView(), "user_rec", users );
    }
}

