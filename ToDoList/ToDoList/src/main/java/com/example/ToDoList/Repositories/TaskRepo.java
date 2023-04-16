package com.example.ToDoList.Repositories;

import com.example.ToDoList.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findByName(String name);
}
