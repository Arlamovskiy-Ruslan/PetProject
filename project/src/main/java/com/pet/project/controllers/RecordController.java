package com.pet.project.controllers;

import com.pet.project.models.User;
import com.pet.project.models.UserRecord;
import com.pet.project.repo.UserRecordRepo;
import com.pet.project.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

    @RequestMapping("/records")
    public String records(Model model , Principal principal) {
        logger.info("User name: {}", principal.getName());
        model.addAttribute("users_rec", userRecordRepo.findByUserUsername(principal.getName()));
        String name = principal.getName();
        model.addAttribute("username",name);
        return "records";
    }

    @RequestMapping("/record/{id}/edit")
    public String recordEdit(@PathVariable(value = "id")long id,Model model){
        if(!userRecordRepo.existsById(id)){
            return "redirect:/user-list";
        }
        Optional<UserRecord> record = userRecordRepo.findById(id);
        ArrayList<UserRecord> res = new ArrayList<>();
        record.ifPresent(res::add);
        model.addAttribute("record_edit",res);
        return "record-edit";
    }


    @RequestMapping(value ="/record/{id}/edit", method = RequestMethod.POST)
    public String recordEditPost(@PathVariable(value = "id")long id,@Valid UserRecord userRecord, Model model){
        recordService.editRecord(id,userRecord);
        return "redirect:/records";
    }

    @RequestMapping("/record-add")
    public String reсord_add(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username",name);
        model.addAttribute("user_rec",new UserRecord());
        return "record-add";

    }

    @RequestMapping(value = "/records/record-add", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('user:read')")
    public String record_add_post(@Valid UserRecord record , Principal principal , Model model) {
        recordService.recordByUsername(record,principal);
        recordService.createRecord(record);
        return "redirect:/records";
    }



}
