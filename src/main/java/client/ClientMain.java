package client;

/**
 * Client Main Program
 * starts Client Threads
 *
 * @author Viktoriia Sulimenko, Marcel Waldau
 */
public class ClientMain {

    /**
     * IP of the server
     */
    private final static String SERVER_IP = "localhost";

    /**
     * Port of the server
     */
    private final static int SERVER_PORT = 1234;

    /**
     * Starts the program, the server must be started before
     *
     * @param args no args
     */
    public static void main(String[] args) {

        Client client = new Client(SERVER_IP, SERVER_PORT, "2018-12-07");
        new Thread(client, "ClientThread-1").start();

        Client client2 = new Client(SERVER_IP, SERVER_PORT, "2018-12-06");
        new Thread(client2, "ClientThread-2").start();

        Client client3 = new Client(SERVER_IP, SERVER_PORT, "2018-12-05");
        new Thread(client3, "ClientThread-3").start();

        Client client4 = new Client(SERVER_IP, SERVER_PORT, "2018-12-04");
        new Thread(client4, "ClientThread-4").start();

        Client client5 = new Client(SERVER_IP, SERVER_PORT, "2018-12-03");
        new Thread(client5, "ClientThread-5").start();

        Client client6 = new Client(SERVER_IP, SERVER_PORT, "2018-12-02");
        new Thread(client6, "ClientThread-6").start();

        Client client7 = new Client(SERVER_IP, SERVER_PORT, "2018-12-01");
        new Thread(client7, "ClientThread-7").start();

        // no values available
        Client client_noValues = new Client(SERVER_IP, SERVER_PORT, "2018-12-31");
        new Thread(client_noValues, "ClientThread-8").start();


    }

}
