package com.example.dynamicJDK;

public class LogTraceImpl implements LogTrace {
    @Override
    public void begin(String message) {
        System.out.println("[BEGIN] " + message);
    }

    @Override
    public void end(String message) {
        System.out.println("[END] " + message);
    }
} 