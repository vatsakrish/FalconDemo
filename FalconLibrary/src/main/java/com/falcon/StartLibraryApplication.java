package com.falcon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StartLibraryApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartLibraryApplication.class, args);
    }

    
    @Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }
}