package com.example.taskManager.dto.taskDTO;

import jakarta.validation.constraints.NotEmpty;

public class TaskDeleteDTO {
    @NotEmpty(message = "name should not be empty")
    private String title;

    public @NotEmpty(message = "name should not be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "name should not be empty") String title) {
        this.title = title;
    }
}
