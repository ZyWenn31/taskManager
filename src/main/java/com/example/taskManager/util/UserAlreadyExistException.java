package com.example.taskManager.util;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
