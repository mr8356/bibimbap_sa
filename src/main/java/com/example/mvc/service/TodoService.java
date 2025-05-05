package com.example.mvc.service;

import com.example.mvc.model.Todo;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final List<Todo> todos = new ArrayList<>();

    public TodoService() {
        // 샘플 데이터
        todos.add(new Todo(1L, "프로젝트 회의", LocalDate.now()));
        todos.add(new Todo(2L, "보고서 작성", LocalDate.now().plusDays(1)));
        todos.add(new Todo(3L, "코드 리뷰", LocalDate.now().plusDays(2)));
    }

    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos);
    }
} 