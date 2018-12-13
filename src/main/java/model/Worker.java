package model;

import controller.TempController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Worker runnable for the server
 * <p>
 * The worker runnable is created and executed after the server has accepted a new view connection
 *
 * @author Viktoriia Sulimenko, Marcel Waldau
 */
public class Worker implements Runnable {

    /**
     * Socket to view
     */
    private final Socket client;

    /**
     * Creates a new Worker runnable
     *
     * @param client the socket of the view
     */
    Worker(Socket client) {
        this.client = client;
    }

    /**
     * The run method creates a new TempController and tries to get the data to a specific date, which is received from the view
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

            controller = new TempController("src/main/resources/values.model");

            response = controller.get(date);

            System.out.println(Thread.currentThread().getName() + ": Server responding to " + client.getInetAddress() + ":" + client.getPort() + " ...");
            oos.writeObject(response);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
