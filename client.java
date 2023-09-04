import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",8000);

        DataInputStream from_server=new DataInputStream(socket.getInputStream());
        DataOutputStream to_server=new DataOutputStream(socket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        String msg;
        while(true)
        {
            msg= sc.nextLine();
            to_server.writeUTF(msg);
            msg=from_server.readUTF();
            System.out.println("Server says "+msg);
            if(msg.equals("Bye"))
            {
                break;
            }
        }


    }
}
