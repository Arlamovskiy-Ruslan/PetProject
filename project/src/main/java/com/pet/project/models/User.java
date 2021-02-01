package com.pet.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(unique = true,nullable = false)
    private String email;

    private String activationCode;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,columnDefinition = "varchar(32) default 'USER'")
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @Column(nullable = false,columnDefinition ="varchar(32) default 'ACTIVE'" )
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true ,fetch = FetchType.EAGER
    )
    private List<UserRecord> userRecords;

}