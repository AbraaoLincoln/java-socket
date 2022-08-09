package main.date_converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final static String address = "127.0.0.1";
    private final static int port = 8080;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(address, port);

        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        output.writeUTF("2022-08-01");

        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        String result = input.readUTF();

        System.out.println("converted date: " + result);

        output.close();
        input.close();
        clientSocket.close();
    }
}
