package com.example.taskManager.util.exception;

public class TaskAlreadyExistException extends RuntimeException{
    public TaskAlreadyExistException(String msg){
        super(msg);
    }
}
