package webserver;


import webserver.handler.*;

import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
	private static final Map<String, Handler> handlers = new HashMap<>();

	static {
		handlers.put("/", new HomeHandler());
		handlers.put("/hello", new HelloHandler());
		handlers.put("/bye", new ByeHandler());
	}

	public static Handler getHandler(String path) {
		return handlers.getOrDefault(path, new NotFoundHandler());
	}
}