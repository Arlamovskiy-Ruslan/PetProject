package com.pet.project.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String problem;

    @Column(nullable = false)
    private String mail;

    @ManyToOne
    private User user;

}
