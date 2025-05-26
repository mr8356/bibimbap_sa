package com.example.dynamicJDK.origin;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void orderItem(String itemId);
}
