package com.example.taskManager.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    public Comments() {
    }

    public Comments(String content, Date created_at, Tasks task, User author) {
        this.content = content;
        this.created_at = created_at;
        this.task = task;
        this.author = author;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Tasks getTask() {
        return task;
    }

    public void setTask(Tasks task) {
        this.task = task;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
