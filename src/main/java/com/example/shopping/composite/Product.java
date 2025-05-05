package com.example.shopping.composite;

public class Product implements ProductComponent {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void print() {
        System.out.println("상품: " + name + ", 가격: " + price + "원");
    }
} 