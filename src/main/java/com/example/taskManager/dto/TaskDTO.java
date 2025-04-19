package com.example.taskManager.dto;

import com.example.taskManager.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

public class TaskDTO {

    private String title;

    private String description;

    private String status;

    private UserDTO author;

    private UserDTO executor;

    private List<CommentDTO> comments = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
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

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public UserDTO getExecutor() {
        return executor;
    }

    public void setExecutor(UserDTO executor) {
        this.executor = executor;
    }
}
