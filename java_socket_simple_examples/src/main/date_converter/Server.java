package main.date_converter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int port = 8080;
    private static int threadCount = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(port);

        while(true) {
            System.out.println("waiting for new connection");
            Socket clientSocket = server.accept();
            startConverting(clientSocket);
        }
    }

    private static void startConverting(Socket clientSocket) {
        System.out.println("Starting thread " + threadCount);

        var workerThread = new WorkerThread(String.valueOf(threadCount), clientSocket);
        workerThread.start();

        threadCount++;
    }
}
