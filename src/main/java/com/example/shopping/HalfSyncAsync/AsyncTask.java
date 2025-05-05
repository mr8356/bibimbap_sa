package com.example.shopping.HalfSyncAsync;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.example.shopping.composite.Cart;
import com.example.shopping.composite.ProductComponent;

public class AsyncTask implements Runnable {
    private final BlockingQueue<ProductComponent> taskQueue;
    private final Cart cart;
    private volatile boolean running = true;

    public AsyncTask(Cart cart) {
        this.cart = cart;
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    public void addTask(ProductComponent product) {
        try {
            taskQueue.put(product);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                ProductComponent product = taskQueue.take();
                System.out.println("비동기 작업: " + product.getName() + " 추가 중...");
                Thread.sleep(1000); // 작업 처리 시간 시뮬레이션
                cart.addItem(product);
                System.out.println("비동기 작업: " + product.getName() + " 추가 완료");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
} 