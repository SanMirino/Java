package com.example.ToDoList.Services;

import com.example.ToDoList.Models.Task;
import com.example.ToDoList.Repositories.TaskRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Сервис для работы с задачами
@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    // Получение всех задач
    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    // Сохранение задачи
    public void saveTask(Task task){
        taskRepo.save(task);
    }

    // Получение задачи по id
    public Task getTaskById(Integer id){
        return taskRepo.findById(id).get();
    }

    // Удаление задачи по id
    public void deleteTaskById(Integer id){taskRepo.deleteById(id);}

    // Получение задач по имени
    public List<Task> getTaskByName(String name) {
        return taskRepo.findByName(name);
    }

}