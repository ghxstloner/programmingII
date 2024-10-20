package unit7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private int clientId;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket, int clientId) {
        this.clientSocket = clientSocket;
        this.clientId = clientId;

        // Initialize the I/O streams in the constructor
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Error initializing I/O streams for client #" + clientId + ": " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            out.println("Welcome! You are Client #" + clientId);
            String message;

            while ((message = in.readLine()) != null) {
                // Handle private messages
                if (message.startsWith("/pm ")) {
                    handlePrivateMessage(message);
                }
                // Handle list request
                else if (message.equalsIgnoreCase("/list")) {
                    sendUserList();
                }
                // Handle regular messages
                else {
                    System.out.println("Client #" + clientId + ": " + message);
                    ChatServer.broadcastMessage("Client #" + clientId + ": " + message, this);
                }
            }
        } catch (IOException e) {
            System.err.println("Error in ClientHandler: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
            ChatServer.removeClient(this);
        }
    }

    private void handlePrivateMessage(String message) {
        // Extract target client ID and message
        String[] parts = message.split(" ", 3);
        if (parts.length >= 3) {
            try {
                int targetClientId = Integer.parseInt(parts[1]);
                String privateMessage = parts[2];

                // Find the target client and send the message
                for (ClientHandler client : ChatServer.clientHandlers) {
                    if (client.getClientId() == targetClientId) {
                        client.sendMessage("Private from Client #" + clientId + ": " + privateMessage);
                        out.println("Private to Client #" + targetClientId + ": " + privateMessage);
                        return;
                    }
                }
                out.println("Client #" + targetClientId + " not found.");
            } catch (NumberFormatException e) {
                out.println("Invalid client ID format.");
            }
        } else {
            out.println("Usage: /pm <clientId> <message>");
        }
    }

    private void sendUserList() {
        StringBuilder userList = new StringBuilder("Connected users: ");
        for (ClientHandler client : ChatServer.clientHandlers) {
            userList.append("#").append(client.getClientId()).append(" ");
        }
        out.println(userList.toString());
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    public int getClientId() {
        return clientId;
    }
}
