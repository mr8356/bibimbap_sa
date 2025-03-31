package webserver.handler;

import java.io.DataOutputStream;
import java.io.IOException;

public class NotFoundHandler implements Handler {
	@Override
	public void handle(DataOutputStream dos) throws IOException {
		byte[] body = "404 Not Found".getBytes();
		dos.writeBytes("HTTP/1.1 404 Not Found\r\n");
		dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
		dos.writeBytes("Content-Length: " + body.length + "\r\n");
		dos.writeBytes("\r\n");
		dos.write(body);
		dos.flush();
	}
}