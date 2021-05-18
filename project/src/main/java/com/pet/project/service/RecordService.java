package com.pet.project.service;

import com.pet.project.models.User;
import com.pet.project.models.UserRecord;
import com.pet.project.repo.UserRecordRepo;
import com.pet.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.validation.Valid;
import java.security.Principal;


@Service
public class RecordService {

    private final MailSender mailSender;

    private final UserRepo userRepo;

    private final UserRecordRepo userRecordRepo;

    @Autowired
    public RecordService(UserRepo userRepo, UserRecordRepo userRecordRepo,MailSender mailSender) {
        this.userRepo = userRepo;
        this.userRecordRepo = userRecordRepo;
        this.mailSender = mailSender;
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
        userRec.setMail(userRecord.getMail());
        userRec.setProblem(userRecord.getProblem());
        userRecordRepo.save(userRec);
        sendRecord(userRecord);
    }
    public void sendRecord(UserRecord userRecord){
        if (!StringUtils.isEmpty(userRecord.getMail())) {
            String message = String.format(
                    "Your entry will be reviewed shortly \n" +
                            "First name:%s \n" +
                            "Name:%s \n" +
                            "Last name:%s \n" +
                            "Problem:%s \n",

                    userRecord.getFirst_name(),
                    userRecord.getName(),
                    userRecord.getLast_name(),
                    userRecord.getProblem());

            mailSender.send(userRecord.getMail(), "Record !!!", message);
        }
    }
}




