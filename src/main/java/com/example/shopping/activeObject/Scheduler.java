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
    }

    public void stop() {
    }

    @Override
    public void run() {
    }
} 