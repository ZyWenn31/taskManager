package com.example.taskManager.util.exception;

public class UserNotValidException extends RuntimeException{
    public UserNotValidException(String msg){
        super(msg);
    }
}
