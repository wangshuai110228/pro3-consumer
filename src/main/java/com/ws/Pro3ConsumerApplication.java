package com.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:dubbo-consumer.xml"})
@EntityScan("com.ws.bean")
public class Pro3ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pro3ConsumerApplication.class, args);
    }

}
