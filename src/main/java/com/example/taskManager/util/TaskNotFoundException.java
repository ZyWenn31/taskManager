package com.example.taskManager.util;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String msg) {
        super(msg);
    }
}
