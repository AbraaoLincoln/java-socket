package com.test.restsocket.service;

import com.test.restsocket.exceptions.InternalServerException;
import com.test.restsocket.infra.SocketPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Service
@Slf4j
public class ConverterService {
    @Autowired
    private SocketPool socketPool;

    public String converter(String dateToConvete) {
        Socket clientSocket = socketPool.borrowSocket();
        String formattedDate = sendDateToTheServerToBeConverte(clientSocket, dateToConvete);
        socketPool.returnSocket(clientSocket);
        return formattedDate;
    }

    private String sendDateToTheServerToBeConverte(Socket clientSocket, String dateToConvete) {
        try {
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            output.writeUTF(dateToConvete);

            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            String serverResponse = input.readUTF();

            log.info("Server response: " + serverResponse);
            return serverResponse;
        } catch (IOException e) {
            log.error("Unable to send request to the server");
            throw new InternalServerException();
        }
    }
}
