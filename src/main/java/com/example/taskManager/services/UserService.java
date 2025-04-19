package com.example.taskManager.services;

import com.example.taskManager.repositories.UsersInterface;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersInterface usersInterface;

    public UserService(UsersInterface usersInterface) {
        this.usersInterface = usersInterface;
    }
}
