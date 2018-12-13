package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Represents a view
 *
 * @author Viktoriia Sulimenko, Marcel Waldau
 */
public class Client implements Runnable {
    /**
     * IP of the server
     */
    private String ip;
    /**
     * Port of the server
     */
    private int port;

    /**
     * Date
     */
    private String date;

    Client(String ip, int port, String date) {
        if (date == null)
            throw new IllegalArgumentException("date is null");
        if (date.isEmpty())
            throw new IllegalArgumentException("date is empty");

        this.ip = ip;
        this.port = port;
        this.date = date;
    }

    /**
     * Sends a date String to the server and waits for the result message
     */
    @Override
    public void run() {
        Socket server = null;
        try {
            server = new Socket(ip, port);

            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(server.getInputStream());

            oos.writeObject(date);

            String message = (String) ois.readObject();

            System.out.println(message);


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while connecting to server: " + e.getMessage());
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    System.out.println("Error while closing the socket: " + e.getMessage());
                }
            }
        }


    }
}
