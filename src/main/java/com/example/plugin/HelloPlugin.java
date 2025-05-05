package com.example.plugin;

import org.springframework.stereotype.Component;

@Component
public class HelloPlugin implements Plugin {
    @Override
    public String getName() { 
        return "hello"; 
    }

    @Override
    public void initialize() {
        System.out.println("HelloPlugin initialized");
    }
    
    @Override
    public String execute() { 
        return "Hello from Plugin!"; 
    }
} 