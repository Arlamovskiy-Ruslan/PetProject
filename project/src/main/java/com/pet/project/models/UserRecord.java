package com.pet.project.models;

import javax.persistence.*;


@Entity
public class UserRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    private String name;

    private String first_name;

    private String last_name;

    private String problem;

    private String mail;

    @ManyToOne
    private User user;

    public UserRecord(Long id, String name, String first_name, String last_name, String problem, String mail, User user) {
        this.id = id;
        this.name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.problem = problem;
        this.mail = mail;
        this.user = user;
    }

    public UserRecord() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
