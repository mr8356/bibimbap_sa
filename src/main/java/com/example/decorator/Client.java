package com.example.decorator;

public class Client {
    private final Component component;

    public Client(Component component) {
        this.component = component;
    }

    public void execute() {
        component.operation();
    }

    public static void main(String[] args) {
        Component real = new RealComponent();
        Component timeDecorator = new TimeDecorator(real);
        Component messageDecorator = new MessageDecorator(timeDecorator);

        Client client = new Client(messageDecorator);
        client.execute();
    }
} 