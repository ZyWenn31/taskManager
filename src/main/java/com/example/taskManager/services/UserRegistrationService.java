package com.example.taskManager.services;

import com.example.taskManager.models.User;
import com.example.taskManager.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistrationService {

    private final UsersRepository usersRepository;

    public UserRegistrationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<User> loadPersonByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public Optional<User> loadUserByUsername(String username){
        return usersRepository.findByUsername(username);
    }
}
