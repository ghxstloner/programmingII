package unit7;

import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader consoleInput;

    public ChatClient(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            consoleInput = new BufferedReader(new InputStreamReader(System.in));

            // Start a thread to listen for incoming messages from the server
            new Thread(new MessageListener()).start();

            // Main loop for user input
            String userInput;
            while ((userInput = consoleInput.readLine()) != null) {
                out.println(userInput);  // Send user input to the server
            }
        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing the client socket: " + e.getMessage());
            }
        }
    }

    private class MessageListener implements Runnable {
        @Override
        public void run() {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    System.out.println(serverMessage);  // Print message from server
                }
            } catch (IOException e) {
                System.err.println("Error reading message from server: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new ChatClient("localhost", 12345);
    }
}
