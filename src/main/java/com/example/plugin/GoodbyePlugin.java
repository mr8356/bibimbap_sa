package com.example.plugin;

import org.springframework.stereotype.Component;


@Component
public class GoodbyePlugin implements Plugin {
    @Override
    public String getName() { 
        return "good bye"; 
    }

    @Override
    public void initialize() {
        System.out.println("GoodbyePlugin initialized");
    }
    
    @Override
    public String execute() { 
        return "Goodbye from Plugin!"; 
    }
} 