package com.example.taskManager.dto.userDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserTasksDTO {
    @NotNull(message = "Username should not be empty")
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, max = 50, message = "Username should be between 3 and 50")
    private String username;


    private boolean pagination;


    private boolean sorting;

    @Size(min = 1)
    private String title;

    private int page;

    private int size;


    public @NotNull(message = "Username should not be empty") @NotEmpty(message = "Username should not be empty") @Size(min = 3, max = 50, message = "Username should be between 3 and 50") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Username should not be empty") @NotEmpty(message = "Username should not be empty") @Size(min = 3, max = 50, message = "Username should be between 3 and 50") String username) {
        this.username = username;
    }


    public boolean isPagination() {
        return pagination;
    }

    public void setPagination( boolean pagination) {
        this.pagination = pagination;
    }


    public boolean isSorting() {
        return sorting;
    }

    public void setSorting(boolean sorting) {
        this.sorting = sorting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
