package webserver.handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/hello")
public class HelloHandler implements Handler {
//	@Override
//	public void handle(DataOutputStream dos) throws IOException {
//		byte[] body = "Hello, World!".getBytes();
//		dos.writeBytes("HTTP/1.1 200 OK \r\n");
//		dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
//		dos.writeBytes("Content-Length: " + body.length + "\r\n");
//		dos.writeBytes("\r\n");
//		dos.write(body);
//		dos.flush();
//	}

	@GetMapping
	public String hello() {
		return "Hello, World!";
	}
}