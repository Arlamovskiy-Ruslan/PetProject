package com.pet.project.models;

import lombok.Data;
import lombok.Value;
import org.springframework.security.authentication.jaas.AuthorityGranter;

import javax.persistence.*;
import java.util.List;



@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,columnDefinition = "varchar(32) default 'USER'")
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @Column(nullable = false,columnDefinition ="varchar(32) default 'ACTIVE'" )
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) { this.role = role; }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User(Role role, Status status) {
        this.role = role;
        this.status = status;
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserRecord> userRecords;

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public List<UserRecord> getUserRecords() {
        return userRecords;
    }

    public void setUserRecords(List<UserRecord> userRecords) {
        this.userRecords = userRecords;
    }
}