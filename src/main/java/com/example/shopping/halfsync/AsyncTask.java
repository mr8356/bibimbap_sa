package com.example.shopping.halfsync;

import com.example.shopping.composite.Cart;
import com.example.shopping.composite.ProductComponent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncTask implements Runnable {
    private final Cart cart;

    public AsyncTask(Cart cart) {
        this.cart = cart;
    }

    public void addTask(ProductComponent product) {

    }

    public void stop() {

    }

    @Override
    public void run() {

    }
} 