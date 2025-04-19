package com.example.taskManager.services;

import com.example.taskManager.models.User;
import com.example.taskManager.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RegistrationService {

    private final UsersRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UsersRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setCreated_at(new Date());
        personRepository.save(person);
    }
}
