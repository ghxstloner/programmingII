
# Online Chat Application

## Overview
This project is a simple online chat application that demonstrates socket programming in Java. It allows multiple clients to connect to a central server, send messages, and receive messages from other users in real-time. The application handles multiple client connections concurrently using multithreading and provides basic functionality for message broadcasting and private messaging.

## Features
- **Server-Client Architecture**: A central server manages connections from multiple clients.
- **Broadcasting**: Messages from one client are sent to all connected clients.
- **Private Messaging**: Clients can send private messages to other clients by using the `/pm <clientId> <message>` command.
- **List Connected Users**: Clients can list all currently connected users by typing `/list`.
- **Multithreading**: Each client is handled in its own thread, ensuring that the server can manage multiple clients concurrently.

## Requirements
- **Java Development Kit (JDK) 8+**
- **Java IDE** (Optional, for running the project in an integrated development environment like IntelliJ IDEA, Eclipse, etc.)

## How to Run the Application

### Step 1: Compile the Project
1. Ensure that the JDK is installed on your system and added to your system's path.
2. Compile the project using the terminal or your preferred Java IDE.

```bash
javac unit7/ChatServer.java unit7/ChatClient.java
```

### Step 2: Run the Server
1. First, you need to start the server, which will accept connections from clients.
2. Open a terminal/command prompt and navigate to the directory containing the compiled files.
3. Start the server by running the following command:

```bash
java unit7.ChatServer
```

You should see the message: `Chat Server is running on port 12345...`.

### Step 3: Run the Client
1. Open another terminal window or tab (or several, depending on how many clients you want to connect).
2. Run the client with the following command:

```bash
java unit7.ChatClient
```

You will see a welcome message indicating that the client has successfully connected to the server and has been assigned a unique client ID (e.g., `Welcome! You are Client #0`).

You can start typing messages, which will be broadcast to all other connected clients.

### Client Commands:
- **Send a message**: Simply type the message and press `Enter`.
- **Private message**: To send a private message to a specific client, use the following format:

```bash
/pm <clientId> <message>
```

For example, `/pm 2 Hello Client 2!` will send a private message to Client #2.

- **List all connected users**: To list all currently connected users, type `/list` and press `Enter`.

### Step 4: Closing the Application
To stop the server or client, simply press `CTRL + C` in the terminal window running the process.

## Implementation Details

### ChatServer.java
- **Purpose**: The server class listens for incoming client connections on port 12345. For each connected client, it starts a new `ClientHandler` thread to manage communication.
- **Broadcasting**: Messages from one client are broadcast to all other clients except the sender.

### ClientHandler.java
- **Purpose**: This class handles the communication for each connected client. It receives messages from the client and forwards them to the server for broadcasting.
- **Private Messaging**: Clients can send private messages to specific users using the `/pm` command.
- **User List**: Clients can request a list of connected users by typing `/list`.

### ChatClient.java
- **Purpose**: The client connects to the server, sends messages, and listens for incoming messages from the server. Messages are read from the console and sent to the server via sockets.

## Example Usage

- **Client #0** connects and sends the message: `Hello! I am Client 0`.
- **Client #1** connects and sends a private message to Client #0 using `/pm 0 Hi Client 0!`.
- Both clients receive the broadcast messages, and Client #0 sees the private message from Client #1.

## Notes
- **Thread Safety**: The `ChatServer` class uses synchronization to manage multiple client connections and prevent race conditions.
- **I/O Management**: Input and output streams are handled using `BufferedReader` and `PrintWriter` to ensure efficient communication between the server and clients.

## Future Improvements
- **Graphical User Interface (GUI)**: A future improvement could be implementing a GUI using JavaFX or Swing for better user experience.
- **File Transfer**: Adding file transfer functionality would allow clients to share files in addition to sending text messages.
