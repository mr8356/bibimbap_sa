package com.example.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDecorator implements Component {
    private static final Logger logger = LoggerFactory.getLogger(MessageDecorator.class);
    private final Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        logger.info("MessageDecorator: 작업 시작");
        component.operation();
        logger.info("MessageDecorator: 작업 종료"); 
    }
}