package main.simple_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    private final static String address = "127.0.0.1";
    private final static int port = 8080;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(address, port);
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());

        //sending data
        output.writeUTF("Hello server");

        //receing data
        String serverMsg = input.readUTF();
        System.out.println("Server say: " + serverMsg);

        //closing connection
        output.close();
        input.close();
        clientSocket.close();
    }
}
