package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.plugin.PluginManager;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        
        // 플러그인 시스템 테스트
        System.out.println("=== 플러그인 시스템 테스트 ===");
        PluginManager pluginManager = context.getBean(PluginManager.class);
        pluginManager.runAll().forEach(System.out::println);
    }
} 