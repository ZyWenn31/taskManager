package com.example.taskManager.util;

import com.example.taskManager.models.User;
import com.example.taskManager.repositories.UsersRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserExistValidator implements Validator {

    private final UsersRepository usersRepository;

    public UserExistValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if (usersRepository.findByUsername(user.getUsername()).isEmpty()){
            throw new UserNotFoundException("user with this username not found");
        }
    }
}
