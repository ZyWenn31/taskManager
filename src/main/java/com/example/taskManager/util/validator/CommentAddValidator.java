package com.example.taskManager.util.validator;

import com.example.taskManager.models.Comments;
import com.example.taskManager.repositories.TasksRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CommentAddValidator implements Validator {

    private final TasksRepository tasksRepository;

    public CommentAddValidator(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Comments.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        /*Comments comment = (Comments) target;

        if (tasksRepository.findByAuthorAndTitle(comment.getAuthor(), comment.get));*/
    }
}
