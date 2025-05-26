package com.example.dynamicJDK;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.dynamicJDK.origin.OrderRepository;
import com.example.dynamicJDK.origin.OrderRepositoryImpl;
import com.example.dynamicJDK.origin.OrderService;
import com.example.dynamicJDK.origin.OrderServiceImpl;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyConfig {
    
    @Bean
    public OrderService orderService(LogTrace logTrace) {
        OrderService orderService = new OrderServiceImpl(orderRepository(logTrace));
        return null;
    }

    @Bean
    public OrderRepository orderRepository(LogTrace logTrace) {
        OrderRepository orderRepository = new OrderRepositoryImpl();
        return null;
    }
} 