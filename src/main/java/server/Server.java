package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server listen on a specific port and starts a new worker thread if a new connection is received
 *
 * @author Viktoriia Sulimenko, Marcel Waldau
 */
public class Server implements Runnable {

    /**
     * The port on which the server listened
     */
    private int port;

    /**
     * Creates a new Server
     *
     * @param port the port on whcih the server listened
     */
    Server(int port) {
        this.port = port;
    }

    /**
     * The run method is waiting for connection in a while loop and every time a
     * new client connection is received it will creates a new worker thread
     */
    @Override
    public void run() {

        try {
            ServerSocket serverSocket;

            serverSocket = new ServerSocket(port);
            System.out.println(Thread.currentThread().getName() + ": Server is running on port " + serverSocket.getLocalPort());

            while (!Thread.currentThread().isInterrupted()) {
                Socket client;

                System.out.println(Thread.currentThread().getName() + ": Server waiting / accepting ...");
                client = serverSocket.accept();
                System.out.println(Thread.currentThread().getName() + ": new client (" + client.getLocalSocketAddress() + ") request received, start new workerThread");

                new Thread(new Worker(client), "Worker").start();
            }

        } catch (IOException e) {
            System.out.println("Error IOException: " + e.getMessage());
        }

    }


}
