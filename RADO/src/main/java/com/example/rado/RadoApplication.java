package com.example.rado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RadoApplication.class, args);
    }

}
