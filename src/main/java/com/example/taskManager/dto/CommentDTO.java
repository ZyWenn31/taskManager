package com.example.taskManager.dto;

import com.example.taskManager.dto.userDTO.UserDTO;

public class CommentDTO {

    private String content;

    private int taskId;

    private UserDTO author;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
