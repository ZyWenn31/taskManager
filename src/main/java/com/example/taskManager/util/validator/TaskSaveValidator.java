package com.example.taskManager.util.validator;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.repositories.TasksRepository;
import com.example.taskManager.util.AuthoriseUser;
import com.example.taskManager.util.exception.TaskAlreadyExistException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TaskSaveValidator implements Validator {
    private final TasksRepository tasksRepository;
    private final AuthoriseUser authoriseUser;

    public TaskSaveValidator(TasksRepository tasksRepository, AuthoriseUser authoriseUser) {
        this.tasksRepository = tasksRepository;
        this.authoriseUser = authoriseUser;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Tasks.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Tasks task = (Tasks) target;


        if (tasksRepository.findByAuthorAndTitle(authoriseUser.getAuthorisePerson(), task.getTitle()).isPresent()){
            throw new TaskAlreadyExistException("Task with this author and title already exist");
        }
        return;
    }
}
