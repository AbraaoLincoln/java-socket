package main.date_converter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkerThread extends Thread{
    private String threadName;
    private Socket clientSocket;

    public WorkerThread(String threadName, Socket clientSocket) {
        this.threadName = threadName;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            while(true) {
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                String dateToConverte = input.readUTF();

                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                output.writeUTF(converter(dateToConverte));
            }

//            input.close();
//            output.close();
//            clientSocket.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Finish connection with the client");
        }
    }

    private String converter(String dateToConverte) {
        System.out.println("Thread " + threadName + " converting date " + dateToConverte);
        //LocalDateTime datetime = LocalDateTime.parse(dateToConverte, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        LocalDate localDate = LocalDate.parse(dateToConverte);
        var dateConverted = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println(dateConverted);
        return dateConverted;
    }
}
