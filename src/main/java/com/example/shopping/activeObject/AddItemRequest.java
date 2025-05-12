package com.example.shopping.activeObject;

import com.example.shopping.composite.Cart;
import com.example.shopping.composite.Product;

public class AddItemRequest{
    private final Cart cart;
    private final Product product;

    public AddItemRequest(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }

    public void execute() {
        System.out.println("상품 추가 요청 실행: " + product.getName());
        cart.addItem(product);
    }
} 