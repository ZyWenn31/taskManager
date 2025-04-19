package com.example.taskManager.util;

import com.example.taskManager.models.User;
import com.example.taskManager.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//Класс для получения авторизированнного пользователя
@Component
public class AuthoriseUser {

    public AuthoriseUser() {
    }

    public User getAuthorisePerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        return  personDetails.getUser();
    }
}
