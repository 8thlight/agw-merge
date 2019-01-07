package core;

import org.junit.Test;
import stubs.ServerSocketStub;
import stubs.ServerStub;
import stubs.SocketStub;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class ServerTest {
    @Test
    public void handlesMultipleConnections() throws IOException {
        SocketStub socket = new SocketStub();
        ServerSocketStub serverSocket = new ServerSocketStub(5000);
        Connection connection = new Connection(serverSocket);

        // Simulates the first accepted connection.
        serverSocket.setConnectionSocket(socket);

        ServerStub server = new ServerStub(connection);
        server.setRequestsToProcess(5);

        server.start();

        assertEquals(5, server.getRequestsProcessed());
    }
}