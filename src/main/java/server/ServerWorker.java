package server;

import csv.TempController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerWorker extends Thread {

    private final Socket client;

    ServerWorker(Socket client) {
        super("Server-WorkerThread");
        this.client = client;
    }


    public void run() {

        String response;
        TempController controller;

        try {

            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

            System.out.println(Thread.currentThread().getName() + ": Server readLine ...");

            String date = (String) ois.readObject();

            controller = new TempController();
            response = controller.get(date);

            System.out.println(Thread.currentThread().getName() + ": Server line readed ...");
            System.out.println(Thread.currentThread().getName() + ": Received: " + date);

            System.out.println(Thread.currentThread().getName() + ": Server responding to client ...");
            oos.writeObject(response);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
