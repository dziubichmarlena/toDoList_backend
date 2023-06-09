package com.example.todolist_backend.service;


import com.example.todolist_backend.model.Task;
import com.example.todolist_backend.model.User;
import com.example.todolist_backend.repo.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAll(){
        return taskRepo.findAll();
    }
    public List<Task> getAllTaskByUser(User user){
        return taskRepo.getAllByUser(user);
    }

    public void saveTask(Task task){
        taskRepo.save(task);
    }

    public Task findTaskById(Long id){
        return taskRepo.findTaskById(id);
    }

    public void deleteTaskById(Long id){
        taskRepo.deleteTaskById(id);
    }

    public List<Task> getAllTaskByUserAndActionOnTask(User user, int actionOnTask){
        return taskRepo.getTasksByUserAndActionOnTask(user, actionOnTask);
    }
}
