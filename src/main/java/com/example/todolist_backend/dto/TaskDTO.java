package com.example.todolist_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {

    private LocalDate date;
    private String content;
}
