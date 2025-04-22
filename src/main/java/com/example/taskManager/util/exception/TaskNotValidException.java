package com.example.taskManager.util.exception;

public class TaskNotValidException extends RuntimeException{
    public TaskNotValidException(String msg){
        super(msg);
    }
}
