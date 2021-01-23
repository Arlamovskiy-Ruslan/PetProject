package com.pet.project.service;

import com.pet.project.models.User;
import com.pet.project.models.UserRecord;
import com.pet.project.repo.UserRecordRepo;
import com.pet.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class RecordService {

    private final UserRepo userRepo;

    private final UserRecordRepo userRecordRepo;

    @Autowired
    public RecordService(UserRepo userRepo, UserRecordRepo userRecordRepo) {
        this.userRepo = userRepo;
        this.userRecordRepo = userRecordRepo;
    }

    public void recordAdd(UserRecord record, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).get();
        record.setUser(user);
        userRecordRepo.save(record);
    }
}
