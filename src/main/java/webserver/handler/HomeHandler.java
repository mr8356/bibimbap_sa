package webserver.handler;

import java.io.DataOutputStream;
import java.io.IOException;

public class HomeHandler implements Handler {
	@Override
	public void handle(DataOutputStream dos) throws IOException {
		byte[] body = "Welcome to the home page!".getBytes();
		dos.writeBytes("HTTP/1.1 200 OK \r\n");
		dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
		dos.writeBytes("Content-Length: " + body.length + "\r\n");
		dos.writeBytes("\r\n");
		dos.write(body);
		dos.flush();
	}
}