package com.pet.project.service;

import com.pet.project.models.User;
import com.pet.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.UUID;

@Service
public class UserService {

    private final MailSender mailSender;

    private final UserRepo userRepo;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(MailSender mailSender,UserRepo userRepo, BCryptPasswordEncoder passwordEncoder){
        this.mailSender = mailSender;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public void create(@Valid User userModel){
        User user = new User();
        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepo.save(user);
        sendActivationCode();

    }

    public void sendActivationCode(){
        User user = new User();
        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n"+
                            "Welcome to PetProject.Please visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code",message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }
    public void editStatusUser(@PathVariable(value = "id")long id,@Valid User userModel){
        User user = userRepo.findById(id).orElseThrow();
        user.setUsername(userModel.getUsername());
        user.setStatus(userModel.getStatus());
        userRepo.save(user);
    }
    public void editRoleUser(@PathVariable(value = "id")long id,@Valid User userr){
        User user = userRepo.findById(id).orElseThrow();
        user.setRole(userr.getRole());
        userRepo.save(user);
    }
}
