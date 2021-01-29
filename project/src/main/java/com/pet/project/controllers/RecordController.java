package com.pet.project.controllers;

import com.pet.project.models.User;
import com.pet.project.models.UserRecord;
import com.pet.project.repo.UserRecordRepo;
import com.pet.project.repo.UserRepo;
import com.pet.project.service.RecordService;
import com.pet.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class RecordController {

    private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

    private final UserRecordRepo userRecordRepo;

    private final RecordService recordService;

    @Autowired
    public RecordController(UserRecordRepo userRecordRepo, RecordService recordService) {
        this.userRecordRepo = userRecordRepo;
        this.recordService = recordService;
    }

    @GetMapping("/records")
    public String records(Model model , Principal principal) {
        logger.info("User name: {}", principal.getName());
        model.addAttribute("users_rec", userRecordRepo.findByUserUsername(principal.getName()));
        String name = principal.getName();
        model.addAttribute("username",name);
        return "records";
    }

    @GetMapping("record-delete/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String deleteUser(@PathVariable("id")long id){
        userRecordRepo.deleteById(id);
        return "redirect:/records";
    }

    @GetMapping("/record/{id}/edit")
    @PreAuthorize("hasAuthority('user:read')")
    public String userEdit(@PathVariable(value = "id")long id,Model model){
        if(!userRecordRepo.existsById(id)){
            return "redirect:/user-list";
        }
        Optional<UserRecord> record = userRecordRepo.findById(id);
        ArrayList<UserRecord> res = new ArrayList<>();
        record.ifPresent(res::add);
        model.addAttribute("record_edit",res);
        return "record-edit";
    }

    @GetMapping("/record-add")
    public String reсord_add(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username",name);
        return "record-add";

    }


    @PostMapping("/record-add")
    public String record_add_post
            (@RequestParam String first_name
            ,@RequestParam String name
            ,@RequestParam String last_name
            ,@RequestParam String problem
            , UserRecord record , Principal principal) {
        recordService.recordAdd(record,principal);
        UserRecord user_rec_add = new UserRecord(first_name,name,last_name,problem);
        userRecordRepo.save(user_rec_add);
        return "redirect:/records";
    }



}
