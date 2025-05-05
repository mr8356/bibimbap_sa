package com.example.plugin;

public interface Plugin {
    String getName();
    void initialize();
    String execute();
} 