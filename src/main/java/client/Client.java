package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {

    private String ip;
    private int port;
    private String date;

    Client(String ip, int port, String date) {
        this.ip = ip;
        this.port = port;
        this.date = date;
    }


    @Override
    public void run() {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(ip, port);

            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

            oos.writeObject(date);

            String message = (String) ois.readObject();

            System.out.println(message);


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while connecting to server: " + e.getMessage());
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Error while closing the socket: " + e.getMessage());
                }
            }
        }


    }
}
