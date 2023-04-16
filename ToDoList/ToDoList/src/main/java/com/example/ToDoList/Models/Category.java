package com.example.ToDoList.Models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public void updateCategory(Category category){
        if (category.name != null) {
            this.name = category.name;
        }
    }
}
