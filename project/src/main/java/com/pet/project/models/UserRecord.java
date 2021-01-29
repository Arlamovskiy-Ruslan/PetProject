package com.pet.project.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    private String name;

    private String first_name;

    private String last_name;

    private String problem;

    @ManyToOne
    private User user;

    public UserRecord() {
    }

    public UserRecord(String first_name, String name, String last_name, String problem) {
    }
}
