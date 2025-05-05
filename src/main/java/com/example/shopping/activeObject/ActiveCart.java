package com.example.shopping.activeObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.shopping.composite.Cart;
import com.example.shopping.composite.Product;

public class ActiveCart {
    private final Cart cart;
    private final Scheduler scheduler;
    private final ExecutorService executorService;

    public ActiveCart(String name) {
        this.cart = new Cart(name);
        this.scheduler = new Scheduler();
        this.executorService = Executors.newSingleThreadExecutor();
        this.executorService.execute(scheduler);
    }

    public void addItem(Product product) {
        scheduler.enqueue(new AddItemRequest(cart, product));
    }

    public void print() {
        cart.print();
    }

    public void shutdown() {
        scheduler.stop();
        executorService.shutdown();
    }
} 