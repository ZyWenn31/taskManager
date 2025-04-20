package com.example.taskManager.util;

import com.example.taskManager.models.User;
import com.example.taskManager.services.UserRegistrationService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserRegistrationService userRegistrationService;

    public UserValidator(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User person = (User)o;

        if(userRegistrationService.loadPersonByEmail(person.getEmail()).isPresent() || userRegistrationService.loadUserByUsername(person.getUsername()).isPresent()){
            errors.rejectValue("email", "", "User with this email already exist");
        }else {
            return;
        }
    }
}
