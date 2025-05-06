package com.example.controller;

import com.example.shopping.composite.Product;
import com.example.shopping.composite.Cart;
import com.example.shopping.halfsync.AsyncTask;
import com.example.shopping.activeObject.ActiveCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class CartController {
    private final Cart halfSyncCart;
    private final AsyncTask asyncTask;
    private final ExecutorService executorService;
    private final ActiveCart activeCart;

    public CartController() {
        this.halfSyncCart = new Cart("Half-Sync/Half-Async 장바구니");
        this.asyncTask = new AsyncTask(halfSyncCart);
        this.executorService = Executors.newSingleThreadExecutor();
        this.executorService.execute(asyncTask);
        this.activeCart = new ActiveCart("Active Object 장바구니");
    }

    @GetMapping("/test-composite")
    public void testComposite() {
        System.out.println("\n=== 컴포지트 패턴 테스트 ===");
        Cart cart = new Cart("컴포지트 장바구니");
        
        Product laptop = new Product("노트북", 1500000);
        Product mouse = new Product("마우스", 50000);
        Product keyboard = new Product("키보드", 100000);

        cart.addItem(laptop);
        cart.addItem(mouse);
        cart.addItem(keyboard);
        
        cart.print();
    }

    @GetMapping("/test-half-sync")
    public void testHalfSync() {
        System.out.println("\n=== Half-Sync/Half-Async 패턴 테스트 ===");
        Product laptop = new Product("노트북", 1500000);
        Product mouse = new Product("마우스", 50000);
        Product keyboard = new Product("키보드", 100000);

        asyncTask.addTask(laptop);
        asyncTask.addTask(mouse);
        asyncTask.addTask(keyboard);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        halfSyncCart.print();
        asyncTask.stop();
        executorService.shutdown();
    }

    @GetMapping("/test-active-object")
    public void testActiveObject() {
        System.out.println("\n=== Active Object 패턴 테스트 ===");
        Product laptop = new Product("노트북", 1500000);
        Product mouse = new Product("마우스", 50000);
        Product keyboard = new Product("키보드", 100000);

        activeCart.addItem(laptop);
        activeCart.addItem(mouse);
        activeCart.addItem(keyboard);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        activeCart.print();
        activeCart.shutdown();
    }
} 