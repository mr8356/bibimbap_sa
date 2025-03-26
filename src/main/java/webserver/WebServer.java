package webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@SpringBootApplication
public class WebServer {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(WebServer.class, args);
    }
}
