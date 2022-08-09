package com.test.restsocket.infra;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class SocketFactory extends BasePooledObjectFactory<Socket> {
    private final static String address = "127.0.0.1";
    private final static int port = 8080;

    @Override
    public Socket create() throws Exception {
        return new Socket(address, port);
    }

    @Override
    public PooledObject<Socket> wrap(Socket socket) {
        return new DefaultPooledObject<Socket>(socket);
    }

//    @Override
//    public void destroyObject(PooledObject<Socket> p) throws Exception {
//        System.out.println("cloing");
//        Socket s = (Socket) p.getObject();
//        s.close();
//    }
}
