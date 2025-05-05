package com.example.mvc.model;

import java.time.LocalDate;

public class Todo {
    private Long id;
    private String title;
    private LocalDate dueDate;

    public Todo(Long id, String title, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
} 