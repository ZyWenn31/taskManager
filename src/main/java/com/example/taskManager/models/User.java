package com.example.taskManager.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @OneToMany(mappedBy = "author")
    List<Tasks> myTasks = new ArrayList<>();

    @OneToMany(mappedBy = "executor")
    List<Tasks> myExecute = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    List<Comments> comments = new ArrayList<>();

    public User(String username, String email, String password, Date created_at) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }

    public User() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public List<Tasks> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(List<Tasks> myTasks) {
        this.myTasks = myTasks;
    }

    public List<Tasks> getMyExecute() {
        return myExecute;
    }

    public void setMyExecute(List<Tasks> myExecute) {
        this.myExecute = myExecute;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
