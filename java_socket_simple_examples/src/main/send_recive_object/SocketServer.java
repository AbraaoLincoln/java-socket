package main.send_recive_object;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private final static int port = 8080;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Staring server on port " + port);

        System.out.println("Waiting for connection");
        Socket clientSocket = serverSocket.accept();

        System.out.println("Client connection accept");
        System.out.println("client: " + clientSocket.getInetAddress().getHostAddress());

        //read the data from the client
        ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
        Message clientMsg = (Message) input.readObject();
        System.out.println("client say: " + clientMsg.getContent());


        //closing connections
        input.close();
        clientSocket.close();
        serverSocket.close();
    }
}
