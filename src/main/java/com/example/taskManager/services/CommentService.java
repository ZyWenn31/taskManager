package com.example.taskManager.services;

import com.example.taskManager.repositories.CommentsRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;

    public CommentService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
}
