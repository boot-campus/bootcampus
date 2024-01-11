package com.boot.campus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CampusApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CampusApplication.class, args);
    }
}
