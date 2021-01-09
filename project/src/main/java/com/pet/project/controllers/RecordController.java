package com.pet.project.controllers;

import com.pet.project.models.User;
import com.pet.project.models.UserRecord;
import com.pet.project.repo.UserRecordRepo;
import com.pet.project.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class RecordController {

    private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

    private final UserRecordRepo userRecordRepo;

    private final UserRepo userRepo;

    public RecordController(UserRecordRepo userRecordRepo, UserRepo userRepo) {
        this.userRecordRepo = userRecordRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/records")
    public String records(Model model , Principal principal) {
        logger.info("User name: {}", principal.getName());
        model.addAttribute("users_rec", userRecordRepo.findByUserUsername(principal.getName()));
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
            , UserRecord record , Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).get();
        record.setUser(user);
        userRecordRepo.save(record);
        UserRecord user_rec_add = new UserRecord(first_name,name,last_name,problem);
        userRecordRepo.save(user_rec_add);
        return "redirect:/records";
    }



}
