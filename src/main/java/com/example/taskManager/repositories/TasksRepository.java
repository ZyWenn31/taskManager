package com.example.taskManager.repositories;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    Optional<Tasks> findByAuthorAndTitle(User author, String title);
    Optional<Tasks> findByExecutorAndTitleOrAuthorAndTitle(User author, String title1 , User executor, String title);
    Optional<Tasks> findByExecutorAndTitle(User executor, String title);

    List<Tasks> findAllByAuthor(User author);
    List<Tasks> findAllByAuthor(User author, Pageable pageable);
    List<Tasks> findAllByAuthorAndTitleContaining(User author, String title);
    List<Tasks> findAllByAuthorAndTitleContaining(User author, String title, Pageable pageable);

    List<Tasks> findAllByExecutor(User executor);
    List<Tasks> findAllByExecutor(User executor, Pageable pageable);
    List<Tasks> findAllByExecutorAndTitleContaining(User executor, String title);
    List<Tasks> findAllByExecutorAndTitleContaining(User executor, String title, Pageable pageable);




    void deleteByTaskId(int id);
}
