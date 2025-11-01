package ACT1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Server {
    static void main() {
        try {
            int port = 1234;
            InetSocketAddress ADDr = new InetSocketAddress(port);
            DatagramSocket server = new DatagramSocket(ADDr);
            System.out.println("Server online on port"+port);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            server.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println("[" + packet.getAddress().getHostAddress()+"]"+
                    ":" + "[" + packet.getPort()+"]"+ " -> " + msg);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
