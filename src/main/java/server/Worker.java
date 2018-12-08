package server;

import csv.TempController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Worker runnable for the server
 * <p>
 * The worker runnable is created and executed after the server has accepted a new client connection
 *
 * @author Viktoriia Sulimenko, Marcel Waldau
 */
public class Worker implements Runnable {

    /**
     * Socket to client
     */
    private final Socket client;

    /**
     * Creates a new Worker runnable
     *
     * @param client the socket of the client
     */
    Worker(Socket client) {
        this.client = client;
    }

    /**
     * The run method creates a new TempController and tries to get the data to a specific date, which is received from the client
     */
    @Override
    public void run() {

        String response;
        TempController controller;

        try {

            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

            String date = (String) ois.readObject();
            System.out.println(Thread.currentThread().getName() + ": From " + client.getInetAddress() + ":" + client.getPort() + " received: " + date);

            controller = new TempController("src/main/resources/values.csv");

            response = controller.get(date);

            System.out.println(Thread.currentThread().getName() + ": Server responding to " + client.getInetAddress() + ":" + client.getPort() + " ...");
            oos.writeObject(response);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
