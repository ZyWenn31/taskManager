package com.example.taskManager.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id", referencedColumnName = "user_id")
    private User executor;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @OneToMany(mappedBy = "task")
    List<Comments> comments = new ArrayList<>();

    public Tasks() {
    }

    public Tasks(String title, String description, String status, User author, User executor, Date created_at, Date updated_at) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.author = author;
        this.executor = executor;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
