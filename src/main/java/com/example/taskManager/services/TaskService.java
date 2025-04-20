package com.example.taskManager.services;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.User;
import com.example.taskManager.repositories.TasksRepository;
import com.example.taskManager.repositories.UsersRepository;
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
    private final UsersRepository usersRepository;

    public TaskService(TasksRepository tasksRepository, AuthoriseUser authoriseUser, UsersRepository usersRepository) {
        this.tasksRepository = tasksRepository;
        this.authoriseUser = authoriseUser;
        this.usersRepository = usersRepository;
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
    public void editStatus(Tasks task){
        Tasks editTask = tasksRepository.findByAuthorAndTitle(authoriseUser.getAuthorisePerson(), task.getTitle()).get();

        editTask.setStatus(task.getStatus());
        editTask.setUpdated_at(new Date());
        tasksRepository.save(editTask);
    }

    @Transactional
    public void editExecutor(Tasks task){
        Tasks editTask = tasksRepository.findByAuthorAndTitle(authoriseUser.getAuthorisePerson(), task.getTitle()).get();
        User newExecutor = usersRepository.findByUsername(task.getExecutor().getUsername()).get();

        editTask.setExecutor(newExecutor);
        editTask.setUpdated_at(new Date());

        tasksRepository.save(editTask);
    }

    @Transactional
    public void editDescription(Tasks tasks){
        Tasks editTask = tasksRepository.findByAuthorAndTitle(authoriseUser.getAuthorisePerson(), tasks.getTitle()).get();

        editTask.setDescription(tasks.getDescription());
        tasksRepository.save(editTask);
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
