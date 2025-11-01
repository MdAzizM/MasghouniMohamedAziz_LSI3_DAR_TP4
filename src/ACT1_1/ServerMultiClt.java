package ACT1_1;

import java.net.*;
import java.util.*;

public class ServerMultiClt {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(1234);
            System.out.println("Serveur UDP démarré sur le port 1234...");

            // Liste des adresses des clients connus
            Set<SocketAddress> clients = new HashSet<>();

            byte[] buffer = new byte[1024];

            while (true) {
                // Réception d’un message
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Extraction du message texte
                String message = new String(packet.getData(), 0, packet.getLength());
                SocketAddress senderAddress = packet.getSocketAddress();

                // Enregistrer le client s’il est nouveau
                if (!clients.contains(senderAddress)) {
                    clients.add(senderAddress);
                    System.out.println("Nouveau client connecté : " + senderAddress);
                }

                System.out.println("Reçu de " + senderAddress + " → " + message);

                // Diffuser le message à tous les autres clients
                for (SocketAddress addr : clients) {
                    if (!addr.equals(senderAddress)) { // ne pas renvoyer à l’expéditeur
                        DatagramPacket outPacket =
                                new DatagramPacket(packet.getData(), packet.getLength(), addr);
                        socket.send(outPacket);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
