import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8000);

        Socket socket=serverSocket.accept();
        DataInputStream from_client=new DataInputStream(socket.getInputStream());
        DataOutputStream to_client=new DataOutputStream(socket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        String msg;
        while(true)
        {
            System.out.println("Server Side");
            msg=from_client.readUTF();
            System.out.println("Client says "+msg);
            if(msg.equals("Bye"))
            {
                break;
            }
            System.out.println("Reply to Client: ");
            msg=sc.nextLine();
            to_client.writeUTF(msg);
        }

    }
}