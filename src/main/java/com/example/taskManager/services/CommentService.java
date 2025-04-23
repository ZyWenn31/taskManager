package com.example.taskManager.services;

import com.example.taskManager.models.Comments;
import com.example.taskManager.repositories.CommentsRepository;
import com.example.taskManager.util.AuthoriseUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;
    private final AuthoriseUser authoriseUser;

    public CommentService(CommentsRepository commentsRepository, AuthoriseUser authoriseUser) {
        this.commentsRepository = commentsRepository;
        this.authoriseUser = authoriseUser;
    }

    @Transactional
    public void save(Comments comments){
        comments.setCreated_at(new Date());
        comments.setAuthor(authoriseUser.getAuthorisePerson());
        commentsRepository.save(comments);
    }
}
