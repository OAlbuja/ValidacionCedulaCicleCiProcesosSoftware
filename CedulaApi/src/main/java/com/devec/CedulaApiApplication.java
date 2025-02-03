package com.devec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("checkstyle:hideutilityclassconstructor")
@SpringBootApplication
public class CedulaApiApplication {

	protected CedulaApiApplication() {
        // Constructor protegido para evitar problemas con Checkstyle y permitir a Spring crear el proxy
    }

    public static void main(final String[] args) {
        SpringApplication.run(CedulaApiApplication.class, args);
    }

}
