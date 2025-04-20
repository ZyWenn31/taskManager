package com.example.taskManager.util;

public class TaskNotValidException extends RuntimeException{
    public TaskNotValidException(String msg){
        super(msg);
    }
}
