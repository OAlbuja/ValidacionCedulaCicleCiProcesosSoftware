package com.devec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("checkstyle:hideutilityclassconstructor")
@SpringBootApplication
public class CedulaApiApplication {

	private CedulaApiApplication() {
        // Constructor privado para ocultar el constructor público por defecto
    }

    public static void main(final String[] args) {
        SpringApplication.run(CedulaApiApplication.class, args);
    }

}
