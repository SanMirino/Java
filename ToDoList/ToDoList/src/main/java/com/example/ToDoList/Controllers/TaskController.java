package com.example.ToDoList.Controllers;

import com.example.ToDoList.Models.Task;
import com.example.ToDoList.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Метод для сохранения задачи
    @PostMapping("/")
    public void save(@RequestBody Task task){
        taskService.saveTask(task);
    }

    // Метод для получения всех задач
    @GetMapping("")
    public List<Task> getAll(){
        return taskService.getAllTask();
    }

    // Метод для удаления задачи по ID
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id) {
        taskService.deleteTaskById(id);
    }

    // Метод для получения задачи по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            Task task = taskService.getTaskById(id);
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
    }

    // Метод для обновления задачи по ID
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Task task, @PathVariable Integer id){
        try {
            Task new_task = taskService.getTaskById(id);
            new_task.updateTask(task);
            taskService.saveTask(new_task);
            return new ResponseEntity<>(new_task, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}