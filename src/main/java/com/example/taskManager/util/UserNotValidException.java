package com.example.taskManager.util;

public class UserNotValidException extends RuntimeException{
    public UserNotValidException(String msg){
        super(msg);
    }
}
