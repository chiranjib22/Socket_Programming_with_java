import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Opening port...\n");
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ioEx) {
            System.out.println("Unable to attach port!");
            System.exit(1);
        }
        do {
            handleClient();
        } while (true);
    }

    private static void handleClient() {
        Socket link = null;
        try {
            link = serverSocket.accept();
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            output.println("Server Date: " + (new Date()).toString());

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n****Closing Connection*****");
                link.close();
            } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}