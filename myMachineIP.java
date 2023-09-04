import java.net.InetAddress;
import java.net.UnknownHostException;

public class myMachineIP {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException unHE) {
            System.out.println("couldn't find the local address!");
        }
    }
}
