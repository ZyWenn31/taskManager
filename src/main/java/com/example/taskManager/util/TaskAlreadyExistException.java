package com.example.taskManager.util;

public class TaskAlreadyExistException extends RuntimeException{
    public TaskAlreadyExistException(String msg){
        super(msg);
    }
}
