package com.example.ToDoList.Services;

import com.example.ToDoList.Models.Category;
import com.example.ToDoList.Repositories.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void saveCategory(Category category){
        categoryRepo.save(category);
    }

    public Category getCategoryById(Integer id){
        return categoryRepo.findById(id).get();
    }

    public void deleteCategoryById(Integer id){
        categoryRepo.deleteById(id);
    }

    public List<Category> getCategoryByFirstName(String name) {
        return categoryRepo.findByName(name);
    }

    public void addCategory(Category category) {
    }

    public void updateCategory(Long id, Category category) {
    }

    public void deleteCategory(Long id) {
    }
}
