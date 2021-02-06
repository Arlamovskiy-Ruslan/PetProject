package com.pet.project.service;

import com.pet.project.models.User;
import com.pet.project.models.UserRecord;
import com.pet.project.repo.UserRecordRepo;
import com.pet.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
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

    public void recordByUsername(UserRecord record, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).get();
        record.setUser(user);
        userRecordRepo.save(record);
    }

    public void createRecord(@Valid UserRecord userRecord) {
        UserRecord userRec = new UserRecord();
        userRec.setFirst_name(userRecord.getFirst_name());
        userRec.setName(userRecord.getName());
        userRec.setLast_name(userRecord.getLast_name());
        userRec.setProblem(userRecord.getProblem());
        userRecordRepo.save(userRec);
    }

    public void editRecord(@PathVariable(value = "id")long id, @Valid UserRecord userRecordd){
        UserRecord userRecord = userRecordRepo.findById(id).orElseThrow();
        userRecord.setFirst_name(userRecordd.getFirst_name());
        userRecord.setName(userRecordd.getName());
        userRecord.setLast_name(userRecordd.getLast_name());
        userRecord.setProblem(userRecordd.getProblem());
        userRecordRepo.save(userRecord);
    }
}
