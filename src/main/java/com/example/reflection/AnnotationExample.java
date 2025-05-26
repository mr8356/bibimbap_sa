package com.example.reflection;

import java.lang.reflect.Method;

public class AnnotationExample {
    public static void main(String[] args) {
        // MyClass의 모든 메소드를 가져옴
        Method[] methods = MyClass.class.getDeclaredMethods();
        
        // for (Method method : methods) {
        //     // 메소드에 MyAnnotation이 있는지 확인
        //     if (method.isAnnotationPresent(MyAnnotation.class)) {
        //         // 어노테이션 정보 가져오기
        //         MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                
        //         // 어노테이션의 값들을 출력
        //         System.out.println("Method: " + method.getName());
        //         System.out.println("Value: " + annotation.value());
        //     }
        // }
    }
} 