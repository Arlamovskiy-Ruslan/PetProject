package com.pet.project.service;

import com.pet.project.models.User;
import com.pet.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(@Valid User userr){
        User user = new User();
        user.setUsername(userr.getUsername());
        user.setPassword(passwordEncoder.encode(userr.getPassword()));
        userRepo.save(user);
    }


}
