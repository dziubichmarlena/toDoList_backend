package com.example.todolist_backend.service;


import com.example.todolist_backend.model.User;
import com.example.todolist_backend.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserByLogin(String login){
        return userRepo.findUserByLogin(login);
    }
}
