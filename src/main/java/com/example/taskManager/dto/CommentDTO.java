package com.example.taskManager.dto;

import com.example.taskManager.dto.userDTO.UserDTO;
import com.example.taskManager.dto.userDTO.UserOutputDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDTO {

    @Size(min = 1, message = "content should not be empty")
    private String content;

    @NotEmpty(message = "title should not be empty")
    private String taskTitle;

    //Это автор задачи а не комментария
    @NotNull(message = "author should not be empty")
    private UserOutputDTO author;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public UserOutputDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserOutputDTO author) {
        this.author = author;
    }
}
