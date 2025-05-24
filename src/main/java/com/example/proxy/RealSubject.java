package com.example.proxy;

public class RealSubject implements Subject {
    @Override
    public Concert getConcert(Long id) {
        try {
            // Simulate slow database access
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Concert(id, "Concert " + id);
    }
} 