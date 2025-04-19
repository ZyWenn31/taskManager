package com.example.taskManager.repositories;

import com.example.taskManager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersInterface extends JpaRepository<User, Integer> {
}
