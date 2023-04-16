package com.example.ToDoList.Repositories;

import com.example.ToDoList.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    List<Category> findByName(String name);
}
