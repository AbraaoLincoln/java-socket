package com.test.restsocket;

import com.test.restsocket.infra.SocketPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
@Slf4j
public class RestSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSocketApplication.class, args);
	}
}
