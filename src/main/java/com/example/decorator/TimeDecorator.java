package com.example.decorator;

public class TimeDecorator implements Component {
    private final Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        long start = System.currentTimeMillis();
        component.operation();
        long end = System.currentTimeMillis();
        System.out.println("TimeDecorator: 실행 시간 = " + (end - start) + "ms");
    }
} 