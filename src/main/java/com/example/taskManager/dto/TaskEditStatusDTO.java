package com.example.taskManager.dto;

import com.example.taskManager.util.StatusEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TaskEditStatusDTO {

    @NotEmpty(message = "name should not be empty")
    private String title;

    @NotNull(message = "status should not be empty")
    private StatusEnum status;

    public @NotEmpty(message = "name should not be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "name should not be empty") String title) {
        this.title = title;
    }

    public @NotNull(message = "status should not be empty") StatusEnum getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "status should not be empty") StatusEnum status) {
        this.status = status;
    }
}
