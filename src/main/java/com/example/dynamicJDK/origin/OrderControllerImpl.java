package com.example.dynamicJDK.origin;

import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor    
public class OrderControllerImpl{
    private final OrderService orderService;

    @GetMapping("/request")
    public String request(@RequestParam String id) {
        orderService.orderItem(id);
        return "ok";
    }
} 