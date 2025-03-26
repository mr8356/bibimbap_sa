package webserver.handler;

import java.io.DataOutputStream;
import java.io.IOException;

public interface Handler {
	void handle(DataOutputStream dos) throws IOException;
}