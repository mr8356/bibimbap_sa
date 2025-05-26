package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import com.example.dynamicJDK.DynamicProxyConfig;
import com.example.dynamicJDK.LogTrace;
import com.example.dynamicJDK.LogTraceImpl;

@SpringBootApplication
@Import(DynamicProxyConfig.class)
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new LogTraceImpl();
    }
}
