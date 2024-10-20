package unit7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static int clientId = 0;  // To assign unique IDs to clients
    static Set<ClientHandler> clientHandlers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Chat Server is running on port 12345...");

        while (true) {
            Socket clientSocket = serverSocket.accept();

            // Create and initialize the client handler before adding to the set
            ClientHandler clientHandler = new ClientHandler(clientSocket, clientId++);
            new Thread(clientHandler).start();

            synchronized (clientHandlers) {
                clientHandlers.add(clientHandler);
                // Notify all clients that a new user has connected
                broadcastMessage("Client #" + clientHandler.getClientId() + " has joined the chat.", null);
            }

            System.out.println("Client #" + clientHandler.getClientId() + " connected.");
        }
    }

    public static synchronized void broadcastMessage(String message, ClientHandler excludeClient) {
        synchronized (clientHandlers) {
            for (ClientHandler client : clientHandlers) {
                if (client != excludeClient) {
                    client.sendMessage(message);
                }
            }
        }
    }

    public static synchronized void removeClient(ClientHandler clientHandler) {
        synchronized (clientHandlers) {
            clientHandlers.remove(clientHandler);
        }
        System.out.println("Client " + clientHandler.getClientId() + " has left.");
    }
}
