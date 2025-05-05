package com.example;

import com.example.plugin.PluginManager;
import com.example.shopping.activeObject.ActiveCart;
import com.example.shopping.composite.Cart;
import com.example.shopping.composite.Product;
import com.example.shopping.halfsync.AsyncTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        
        // 플러그인 시스템 테스트
        System.out.println("=== 플러그인 시스템 테스트 ===");
        PluginManager pluginManager = context.getBean(PluginManager.class);
        pluginManager.runAll().forEach(System.out::println);

        // 상품 생성
        Product laptop = new Product("노트북", 1000000);
        Product mouse = new Product("마우스", 30000);
        Product keyboard = new Product("키보드", 50000);

        // 1. 컴포지트 패턴 테스트
        System.out.println("\n=== 1. 컴포지트 패턴 테스트 ===");
        Cart cart = new Cart("컴포지트 장바구니");
        cart.addItem(laptop);
        cart.addItem(mouse);
        cart.addItem(keyboard);
        cart.print();

        // 2. Half-Sync/Half-Async 패턴 테스트
        System.out.println("\n=== 2. Half-Sync/Half-Async 패턴 테스트 ===");
        Cart asyncCart = new Cart("Half-Sync/Half-Async 장바구니");
        AsyncTask asyncTask = new AsyncTask(asyncCart);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(asyncTask);

        // 비동기 작업 추가
        asyncTask.addTask(laptop);
        asyncTask.addTask(mouse);
        asyncTask.addTask(keyboard);

        // 비동기 작업이 완료될 때까지 대기
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        asyncCart.print();
        asyncTask.stop();
        executorService.shutdown();

        // 3. Active Object 패턴 테스트
        System.out.println("\n=== 3. Active Object 패턴 테스트 ===");
        ActiveCart activeCart = new ActiveCart("Active Object 장바구니");
        
        // 상품 추가 (비동기적으로 처리됨)
        activeCart.addItem(laptop);
        activeCart.addItem(mouse);
        activeCart.addItem(keyboard);
        
        // 요청이 처리될 때까지 대기
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        activeCart.print();
        activeCart.shutdown();
    }
} 