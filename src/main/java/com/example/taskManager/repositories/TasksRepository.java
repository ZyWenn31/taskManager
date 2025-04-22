package com.example.taskManager.repositories;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    Optional<Tasks> findByAuthorAndTitle(User author, String title);
    Optional<Tasks> findByExecutorAndTitleOrAuthorAndTitle(User author, String title1 , User executor, String title);
    Optional<Tasks> findByExecutorAndTitle(User executor, String title);
    List<Tasks> findAllByAuthor(User author);
    List<Tasks> findAllByExecutor(User executor);
    void deleteByTaskId(int id);
}
