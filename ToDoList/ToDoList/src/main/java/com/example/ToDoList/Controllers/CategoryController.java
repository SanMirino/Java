package com.example.ToDoList.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ToDoList.Services.CategoryService;
import org.springframework.web.bind.annotation.*;
import com.example.ToDoList.Models.Category;
import java.util.List;
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    // Получение всех категорий
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Получение категории по id
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }
    // Добавление новой категории
    @PostMapping
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }
    // Обновление категории по id
    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
    }
    // Удаление категории по id
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
