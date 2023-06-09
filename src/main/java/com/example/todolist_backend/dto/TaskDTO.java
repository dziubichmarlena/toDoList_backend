package com.example.todolist_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private LocalDate date;
    private String taskContent;
    private int actionOnTask;
    private int priority;
}
