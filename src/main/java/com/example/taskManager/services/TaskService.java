package com.example.taskManager.services;

import com.example.taskManager.repositories.TasksRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TasksRepository tasksRepository;

    public TaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }
}
