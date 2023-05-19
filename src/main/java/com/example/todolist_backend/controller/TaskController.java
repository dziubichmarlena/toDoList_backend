package com.example.todolist_backend.controller;


import com.example.todolist_backend.dto.TaskDTO;
import com.example.todolist_backend.model.Task;
import com.example.todolist_backend.model.User;
import com.example.todolist_backend.service.TaskService;
import com.example.todolist_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private TaskService taskService;
    private UserService userService;

   @GetMapping("/tasks")
    public List<TaskDTO> getAllTasks(@AuthenticationPrincipal User user){
       return taskService.getAllTaskByUser(user)
               .stream()
               .map(task -> new TaskDTO(task.getDate(), task.getTaskContent()))
               .toList();
   }


   @PostMapping(value = "addTask")
    public void addTask(@AuthenticationPrincipal User user, @RequestBody Task task){
        task.setUser(user);
        taskService.saveTask(task);
   }
}
