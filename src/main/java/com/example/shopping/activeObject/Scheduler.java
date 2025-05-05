package com.example.shopping.activeObject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler implements Runnable {
    private final BlockingQueue<MethodRequest> queue;
    private volatile boolean running = true;

    public Scheduler() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public void enqueue(MethodRequest request) {
        try {
            queue.put(request);
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
                MethodRequest request = queue.take();
                request.execute();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
} 