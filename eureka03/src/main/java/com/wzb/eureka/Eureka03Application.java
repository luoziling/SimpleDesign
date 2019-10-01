package com.wzb.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Eureka03Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka03Application.class, args);
    }

}
