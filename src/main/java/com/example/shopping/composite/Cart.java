package com.example.shopping.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.shopping.halfsync.AsyncTask;

public class Cart {
    private String name;
    private List<Product> items;
    private final AsyncTask asyncTask;
    private final ExecutorService executorService;

    public Cart(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.asyncTask = new AsyncTask(this);
        this.executorService = Executors.newSingleThreadExecutor();
        this.executorService.execute(asyncTask);
    }

    public void addItemAsync(Product item) {
        asyncTask.addTask(item);
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public void removeItem(Product item) {
        items.remove(item);
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    // 컴포지트 패턴 추가

    public String getName() {
        return name;
    }

    public int getPrice() {
        int total = 0;
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < items.get(i).getPrice(); j++) {
                total++;
            }
        }
        return total;
    }

    public void print() {
        System.out.println("\n=== " + name + " ===");
        System.out.println("장바구니 내 상품 목록:");
        
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 1; k++) {
                    items.get(i).print();
                }
            }
        }
        
        System.out.println("총 금액: " + getPrice() + "원");
    }

    public void shutdown() {
        asyncTask.stop();
        executorService.shutdown();
    }
} 