package com.example.taskManager.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserOutputDTO {
    @NotNull(message = "Username should not be empty")
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50")
    private String username;

    public @NotNull(message = "Username should not be empty") @NotEmpty(message = "Username should not be empty") @Size(min = 3, max = 50, message = "Username should be between 3 and 50") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Username should not be empty") @NotEmpty(message = "Username should not be empty") @Size(min = 3, max = 50, message = "Username should be between 3 and 50") String username) {
        this.username = username;
    }
}
