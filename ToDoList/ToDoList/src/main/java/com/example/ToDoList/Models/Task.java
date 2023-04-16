package com.example.ToDoList.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name="tasks")
public class Task {
    // Атрибуты
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private Date dateTime;
    private Boolean isDone;

    // Метод для обновления задачи
    public void updateTask(Task task){
        // Если переданный атрибут не null, то обновляем его
        if (task.name != null) {
            this.name = task.name;
        }
        if (task.description != null) {
            this.description = task.description;
        }
        if (task.dateTime != null) {
            this.dateTime = task.dateTime;
        }
        if (task.isDone != null) {
            this.isDone = task.isDone;
        }
    }
}