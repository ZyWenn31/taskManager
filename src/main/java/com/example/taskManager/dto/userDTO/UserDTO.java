package com.example.taskManager.dto.userDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotNull(message = "Username should not be empty")
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50")
    private String username;

    @Email
    @NotNull(message = "Email should not be empty")
    @NotEmpty(message = "Email should not be empty")
    @Size(max = 100, message = "Email should be greater then 50")
    private String email;

    @NotNull(message = "Password should not be empty")
    @NotEmpty(message = "Password should not be empty")
    @Column(name = "password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
