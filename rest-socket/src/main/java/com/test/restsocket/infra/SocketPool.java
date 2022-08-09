package com.test.restsocket.infra;

import com.test.restsocket.exceptions.InternalServerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.Socket;

@Component
@Slf4j
public class SocketPool {
    private ObjectPool<Socket> pool;

    public SocketPool() {
        this.pool = new GenericObjectPool<Socket>(new SocketFactory());
    }

    public Socket borrowSocket() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            log.error("Unable to get socket from the pool");
            throw new InternalServerException();
        }
    }

    public void returnSocket(Socket socket) {
        if(socket == null) {
            log.error("the socket is null it can't be return to the pool");
        }

        try {
            pool.returnObject(socket);
        } catch (Exception e) {
            log.error("Unable to return socket to the pool");
            throw new InternalServerException();
        }
    }

    @PreDestroy
    public void shutdown() {
        log.info("closing socket pool");
        pool.close();;
    }
}
