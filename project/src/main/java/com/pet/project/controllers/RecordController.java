package com.pet.project.controllers;

import com.pet.project.models.UserRecord;
import com.pet.project.repo.UserRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecordController {
    @Autowired
    private UserRecordRepo userRecordRepo;

    @GetMapping("/records")
    public String records(Model model) {
        Iterable<UserRecord> users_rec = userRecordRepo.findAll();
        model.addAttribute("users_rec", users_rec);
        return "records";
    }

    @GetMapping("/record-add")
    public String reсord_add(Model model) {
        return "record-add";
    }


    @PostMapping("/record-add")
    public String record_add_post
            (@RequestParam String first_name
            ,@RequestParam String name
            ,@RequestParam String last_name
            ,@RequestParam String problem
            , Model model) {
        UserRecord user_rec_add = new UserRecord(first_name,name,last_name,problem);
        userRecordRepo.save(user_rec_add);
        return "redirect:/records";
    }



}
