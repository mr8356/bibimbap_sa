package webserver;

import webserver.handler.Handler;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler implements Runnable{
    Socket connection;
    private static final Logger log = Logger.getLogger(RequestHandler.class.getName());

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }
    @Override
    public void run() {
        log.log(Level.INFO, "New Client Connect! Connected IP : " + connection.getInetAddress() + ", Port : " + connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            DataOutputStream dos = new DataOutputStream(out);

            String line = br.readLine();
            if (line == null || line.isEmpty()) return;

            String[] tokens = line.split(" ");
            String url = tokens[1];

            // Dispatcher를 통해 해당 요청을 처리할 핸들러 선택
            Handler handler = Dispatcher.getHandler(url);
            handler.handle(dos);

        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
