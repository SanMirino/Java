package com.example.ToDoList.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String login;

    public void updateUser(User user){
        if (user.firstName != null) {
            this.firstName = user.firstName;
        }
        if (user.lastName != null) {
            this.lastName = user.lastName;
        }
        if (user.middleName != null) {
            this.middleName = user.middleName;
        }
        if (user.birthDate != null) {
            this.birthDate = user.birthDate;
        }
        if (user.login != null) {
            this.login = user.login;
        }
    }
}
