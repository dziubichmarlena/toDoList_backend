package com.example.todolist_backend.controller;


import com.example.todolist_backend.dto.UpdateTaskDTO;
import com.example.todolist_backend.dto.TaskDTO;
import com.example.todolist_backend.model.Task;
import com.example.todolist_backend.model.User;
import com.example.todolist_backend.service.TaskService;
import com.example.todolist_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private TaskService taskService;
    private UserService userService;

    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasks(@AuthenticationPrincipal User user) {
        return taskService.getAllTaskByUser(user)
                .stream()
                .map(task -> new TaskDTO(task.getId(), task.getDate(), task.getTaskContent(), task.getActionOnTask(), task.getPriority()))
                .toList();
    }


    @PostMapping("/tasks")
    public void addTask(@AuthenticationPrincipal User user, @RequestBody Task task) {
        task.setDate(task.getDate().plusDays(1));
        task.setUser(user);
        taskService.saveTask(task);
    }

    @PutMapping("/tasks/{taskId}")
    public void updateTaskPriority(@AuthenticationPrincipal User user, @PathVariable("taskId") Long id,
                                   @RequestBody UpdateTaskDTO updateTaskDTO) {
        Task task = taskService.findTaskById(id);
        task.setPriority(updateTaskDTO.getTaskPriority());
        task.setActionOnTask(updateTaskDTO.getActionOnTask());
        taskService.saveTask(task);
    }

    @DeleteMapping("tasks/{taskId}")
    @Transactional
    public void deleteTask(@AuthenticationPrincipal User user, @PathVariable("taskId") Long id) {
        System.out.println(id);
        taskService.deleteTaskById(id);
    }
}
