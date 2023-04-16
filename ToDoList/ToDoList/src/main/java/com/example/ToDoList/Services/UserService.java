package com.example.ToDoList.Services;

import com.example.ToDoList.Models.User;
import com.example.ToDoList.Repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public User getUserById(Integer id){
        return userRepo.findById(id).get();
    }

    public void deleteUserById(Integer id){
        userRepo.deleteById(id);
    }

    public List<User> getUsersByFirstName(String firstName) {
        return userRepo.findByFirstName(firstName);
    }


}
