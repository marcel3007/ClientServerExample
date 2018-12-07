package server;

public class ServerMain {

    private final static int PORT = 1234;

    public static void main(String[] args) {
        Server server = new Server(PORT);

        Thread serverThread = new Thread(server, "ServerMainThread");
        serverThread.start();

    }
}
