package com.example.taskManager.dto.taskDTO.taskEditDTO;

import com.example.taskManager.dto.userDTO.UserOutputDTO;
import jakarta.validation.constraints.NotEmpty;

public class TaskEditExecutorDTO {

    @NotEmpty(message = "name should not be empty")
    private String title;

    private UserOutputDTO executor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserOutputDTO getExecutor() {
        return executor;
    }

    public void setExecutor(UserOutputDTO executor) {
        this.executor = executor;
    }
}
