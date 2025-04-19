package com.example.taskManager.controllers;

import com.example.taskManager.util.AuthoriseUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final AuthoriseUser authoriseUser;

    public TestController(AuthoriseUser authoriseUser) {
        this.authoriseUser = authoriseUser;
    }

    @GetMapping
    public String test(){
        return authoriseUser.getAuthorisePerson().getUsername();
    }
}
