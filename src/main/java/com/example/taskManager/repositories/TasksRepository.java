package com.example.taskManager.repositories;

import com.example.taskManager.models.Tasks;
import com.example.taskManager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    Optional<Tasks> findByAuthorAndTitle(User author, String title);
}
