package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private int port;

    Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        try {
            startServer();
        } catch (IOException e) {
            System.out.println("Error IOException: " + e.getMessage());
        }

    }

    private void startServer() throws IOException {
        System.out.println(Thread.currentThread().getName() + ": Server running ...");
        ServerSocket serverSocket;

        serverSocket = new ServerSocket(port);

        while (true) {
            Socket client;
            System.out.println(Thread.currentThread().getName() + ": Server waiting ...");
            client = serverSocket.accept();

            System.out.println(Thread.currentThread().getName() + ": new client request received, start new workerThread ...");

            new ServerWorker(client).start();
        }
    }

}
