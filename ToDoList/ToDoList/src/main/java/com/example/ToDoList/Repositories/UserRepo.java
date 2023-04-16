package com.example.ToDoList.Repositories;

import com.example.ToDoList.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByFirstName(String firstName);
}
