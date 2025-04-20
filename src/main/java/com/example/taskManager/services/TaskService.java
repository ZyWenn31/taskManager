package com.example.taskManager.services;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.repositories.TasksRepository;
import com.example.taskManager.util.AuthoriseUser;
import com.example.taskManager.util.StatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    private final TasksRepository tasksRepository;
    private final AuthoriseUser authoriseUser;

    public TaskService(TasksRepository tasksRepository, AuthoriseUser authoriseUser) {
        this.tasksRepository = tasksRepository;
        this.authoriseUser = authoriseUser;
    }

    public List<Tasks> findAll(){
        return tasksRepository.findAll();
    }

    public List<Tasks> findMyTasks(){
        return tasksRepository.findAllByAuthor(authoriseUser.getAuthorisePerson());
    }

    @Transactional
    public void delete(Tasks tasks){
        tasksRepository.deleteByTaskId(tasksRepository.findByAuthorAndTitle(authoriseUser.getAuthorisePerson(), tasks.getTitle()).get().getTask_id());
    }


    @Transactional
    public void save(Tasks tasks){
        enrichTask(tasks);
        tasksRepository.save(tasks);
    }

    private void  enrichTask(Tasks tasks){
        tasks.setStatus(StatusEnum.OPEN);
        tasks.setAuthor(authoriseUser.getAuthorisePerson());
        tasks.setExecutor(authoriseUser.getAuthorisePerson());
        tasks.setCreated_at(new Date());
        tasks.setUpdated_at(new Date());
    }
}
