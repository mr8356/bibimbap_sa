package com.example.reflection;

import java.lang.reflect.Method;

// 리플렉션 기초 예제

public class MethodReflectionExample {
    public static void main(String[] args) {
        try {
            // MyClass의 인스턴스 생성
            Class<?> clazz = Class.forName("com.example.reflection.MyClass");
            Object myClass = clazz.getDeclaredConstructor().newInstance();
            
            // 메소드 순차적으로 실행 (A -> B -> C)
            Method methodA = MyClass.class.getDeclaredMethod("methodA");
            Method methodB = MyClass.class.getDeclaredMethod("methodB"); 
            Method methodC = MyClass.class.getDeclaredMethod("methodC");
            
            methodA.invoke(myClass);
            methodB.invoke(myClass);
            methodC.invoke(myClass);
            
        } catch (Exception e) {
            System.out.println("에러 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}