package com.pet.project.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String comment;

    @ManyToOne
    private User user;

    public Comments() { }

    public Comments(Long id, String comment, User user) {
        this.id = id;
        this.comment = comment;
        this.user = user;
    }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
