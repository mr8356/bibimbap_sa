package com.example.reflection;

public class MyClass {
    @MyAnnotation(value = "Hello")
    public void methodA() {
        System.out.println("methodA 실행");
    }

    @MyAnnotation(value = "World")
    public void methodB() {
        System.out.println("methodB 실행");
    }

    public void methodC() {
        System.out.println("methodC 실행");
    }
} 