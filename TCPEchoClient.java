/* Clients that can send message to the server and get the same message
 *
 * Step 1: Establish a connection to the server.
 * Step 2: Setup input and output stream.
 * Step 3: Send and receive data.
 * Step 4: Close the connection. 
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPEchoClient {
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }

        accessServer();
    }

    private static void accessServer() {
        Socket link = null; // step 1
        try {
            link = new Socket(host, PORT); // step 1
            Scanner input = new Scanner(link.getInputStream()); // step 2
            PrintWriter output = new PrintWriter(link.getOutputStream(), true); // step 2

            // setup stream for keyboard entry
            Scanner sc = new Scanner(System.in);
            String userMsg, response;
            do {
                System.out.println("Enter Message: ");
                userMsg = sc.nextLine();
                output.println(userMsg); // step 3
                response = input.nextLine(); // step 3
                System.out.println("\nSERVER: " + response);
            } while (!userMsg.equals("CLOSE"));

            sc.close();

        } catch (IOException ioEX) {
            ioEX.printStackTrace();

        } finally {
            try {
                System.out.println("\n**** Closing Connection ****");
                link.close(); // step 4
            } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
