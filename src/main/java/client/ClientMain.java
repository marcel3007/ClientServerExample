package client;

public class ClientMain {

    private final static String SERVER_IP = "localhost";
    private final static int SERVER_PORT = 1234;


    public static void main(String[] args) {


        Client client = new Client(SERVER_IP, SERVER_PORT, "2018-12-07");
        new Thread(client, "ClientThread-1").start();

        Client client2 = new Client(SERVER_IP, SERVER_PORT, "2018-12-06");
        new Thread(client2, "ClientThread-2").start();


        Client client3 = new Client(SERVER_IP, SERVER_PORT, "2018-12-05");
        new Thread(client3, "ClientThread-3").start();


        // no values available
        Client client4 = new Client(SERVER_IP, SERVER_PORT, "2018-12-31");
        new Thread(client4, "ClientThread-4").start();
    }

}
