package main.send_recive_object;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {
    private final static String address = "127.0.0.1";
    private final static int port = 8080;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(address, port);
        ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());

        //sending data
        Message msg = new Message("hello server");
        output.writeObject(msg);

        //closing connection
        output.close();
        clientSocket.close();
    }
}
