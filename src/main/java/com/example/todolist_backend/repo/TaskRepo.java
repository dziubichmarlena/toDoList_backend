package com.example.todolist_backend.repo;

import com.example.todolist_backend.model.Task;
import com.example.todolist_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> getAllByUser(User user);

    List<Task> getTasksByUserAndActionOnTask(User user, int actionOnTask);

    Task findTaskById(Long id);

    void deleteTaskById(Long id);
}
