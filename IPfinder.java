import java.net.*;
import java.util.*;

public class IPfinder {
    public static void main(String[] args) {
        String host;
        Scanner sc = new Scanner(System.in);
        InetAddress address;

        System.out.println("\nEnter host name: ");
        host = sc.next();

        try {
            address = InetAddress.getByName(host);
            System.out.println("IP address : " + address.toString());
        } catch (UnknownHostException uhEx) {
            System.out.println("Could not find " + host);
        }
        sc.close();
    }
}