package com.example.taskManager.util.validator;

import com.example.taskManager.models.User;
import com.example.taskManager.repositories.UsersRepository;
import com.example.taskManager.util.exception.UserNotFoundException;
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
            throw new UserNotFoundException("user with username '"+ user.getUsername() +"' not found");
        }
    }
}
