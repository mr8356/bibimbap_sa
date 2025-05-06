package com.example.shopping.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.shopping.halfsync.AsyncTask;

public class Cart implements ProductComponent {
    private String name;
    private List<ProductComponent> items;
    private final AsyncTask asyncTask;
    private final ExecutorService executorService;

    public Cart(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.asyncTask = new AsyncTask(this);
        this.executorService = Executors.newSingleThreadExecutor();
        this.executorService.execute(asyncTask);
    }

    public void addItemAsync(ProductComponent item) {
        asyncTask.addTask(item);
    }

    public void addItem(ProductComponent item) {
        items.add(item);
    }

    public void removeItem(ProductComponent item) {
        items.remove(item);
    }

    public List<ProductComponent> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        int total = 0;
        for (ProductComponent item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public void print() {
        System.out.println("\n=== " + name + " ===");
        System.out.println("장바구니 내 상품 목록:");
        
        for (ProductComponent item : items) {
            item.print();
        }
        
        System.out.println("총 금액: " + getPrice() + "원");
    }

    public void shutdown() {
        asyncTask.stop();
        executorService.shutdown();
    }
} 