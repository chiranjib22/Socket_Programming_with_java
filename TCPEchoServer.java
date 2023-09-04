/* server that echoes back client's message:
 *
 * Step 1: Create a ServerSocket object
 * Step 2: Put server into waiting state
 * Step 3: Setup input and output stream
 * Step 4: Send and Receive data
 * Step 5: Close the connection
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPEchoServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Opening Port...\n");
        try {
            serverSocket = new ServerSocket(PORT); // step 1
        } catch (IOException ioEx) {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
        do {
            handleClient();
        } while (true);
    }

    private static void handleClient() {
        Socket link = null; // step 2
        try {
            link = serverSocket.accept(); // step 2

            Scanner input = new Scanner(link.getInputStream()); // step 3
            PrintWriter output = new PrintWriter(link.getOutputStream(), true); // step 3

            int numMessage = 0;
            String message = input.nextLine(); // step 4
            Scanner sc = new Scanner(System.in);
            while (!message.equals("CLOSE")) {
                System.out.println("Message Received: " + message);
                numMessage++;
                System.out.print("Reply: ");
                String serverMsg = sc.nextLine();
                // output.println("Message " + numMessage + ": " + message); // step 4
                output.println(serverMsg); // step 4

                message = input.nextLine(); // for the next message
            }
            sc.close();
            output.println(numMessage + " messages received"); // step 4
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n**** Closing Connection ****");
                link.close(); // step 5
            } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}