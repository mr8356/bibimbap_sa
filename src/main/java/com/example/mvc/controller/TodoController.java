package com.example.mvc.controller;

import com.example.mvc.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/list")
    public String listView(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "todo/list";
    }

    @GetMapping("/calendar")
    public String calendarView(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "todo/calendar";
    }
} 