package com.example.dynamicJDK.origin;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    void save(String itemId);
}
