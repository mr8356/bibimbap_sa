package com.example.proxy;

public class Client {
    private final Subject subject;

    public Client(Subject subject) {
        this.subject = subject;
    }

    public void request(Long id) {
        Concert concert = subject.getConcert(id);
        System.out.println("Result: " + concert);
    }

    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        Client client = new Client(realSubject);

        System.out.println("request 1:");
        client.request(1L);
        System.out.println("request 2:");
        client.request(1L);
        System.out.println("request 3:");
        client.request(1L);
    }
}