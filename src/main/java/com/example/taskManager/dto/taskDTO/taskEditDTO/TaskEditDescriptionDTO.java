package com.example.taskManager.dto.taskDTO.taskEditDTO;

import jakarta.validation.constraints.NotEmpty;

public class TaskEditDescriptionDTO {

    @NotEmpty(message = "name should not be empty")
    private String title;

    private String description;

    public @NotEmpty(message = "name should not be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "name should not be empty") String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
