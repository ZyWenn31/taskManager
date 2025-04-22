package com.example.taskManager.util.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String msg) {
        super(msg);
    }
}
