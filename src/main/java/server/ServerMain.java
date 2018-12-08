package server;

/**
 * Server Main Program
 * starts a new Server Thread
 *
 * @author Viktoriia Sulimenko, Marcel Waldau
 */
public class ServerMain {

    /**
     * Server Port
     */
    private final static int PORT = 1234;

    /**
     * Starts thr program
     * @param args no args
     */
    public static void main(String[] args) {
        Thread serverThread = new Thread(new Server(PORT), "Server");
        serverThread.start();

    }
}
