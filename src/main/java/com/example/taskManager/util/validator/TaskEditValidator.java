package com.example.taskManager.util.validator;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.repositories.TasksRepository;
import com.example.taskManager.util.AuthoriseUser;
import com.example.taskManager.util.exception.TaskNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TaskEditValidator implements Validator {

    private final TasksRepository tasksRepository;
    private final AuthoriseUser authoriseUser;


    public TaskEditValidator(TasksRepository tasksRepository, AuthoriseUser authoriseUser) {
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

        if (tasksRepository.findByAuthorAndTitle(authoriseUser.getAuthorisePerson(), task.getTitle()).isEmpty() && tasksRepository.findByExecutorAndTitle(authoriseUser.getAuthorisePerson(), task.getTitle()).isEmpty()){
            throw new TaskNotFoundException("Task with this title and user not found");
        }
    }
}
