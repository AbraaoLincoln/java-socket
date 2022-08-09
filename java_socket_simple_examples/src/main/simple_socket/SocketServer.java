package main.simple_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private final static int port = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Staring server on port " + port);

        System.out.println("Waiting for connection");
        Socket clientSocket = serverSocket.accept();

        System.out.println("Client connection accept");
        System.out.println("client: " + clientSocket.getInetAddress().getHostAddress());

        //read the data from the client
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        String clientMsg = input.readUTF();
        System.out.println("client say: " + clientMsg);

        //send data to the client
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        output.writeUTF("hello client");

        //closing connections
        input.close();
        output.close();
        clientSocket.close();
        serverSocket.close();
    }
}
