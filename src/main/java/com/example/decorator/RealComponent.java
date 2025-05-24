package com.example.decorator;

public class RealComponent implements Component {
    @Override
    public void operation() {
        System.out.println("RealComponent: 실제 작업 실행");
    }
} 