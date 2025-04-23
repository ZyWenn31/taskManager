package com.example.taskManager.services;

import com.example.taskManager.models.User;
import com.example.taskManager.repositories.UsersRepository;
import com.example.taskManager.util.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public User findUserByUsername(String username){
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("user with name " + username + "not found"));
    }
}
