package ACT1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    static void main() {
        try{
            int port = 1234;
            Scanner scn = new Scanner(System.in);
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");


            System.out.print("put ur username: ");
            String username = scn.nextLine();
            System.out.println("put ur msg: ");
            String body = scn.nextLine();

            String request = "["+ username + "]: "+ body;
            byte[] buffer = request.getBytes();

            DatagramPacket Cltpacket = new DatagramPacket(buffer, buffer.length, serverAddress, port);
            socket.send(Cltpacket);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
