package com.falcon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StartAuthorApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartAuthorApplication.class, args);
    }

    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(AuthorRepository repository) {
        return args -> {
            repository.save(new Author("Falcon", "Toronto", 40));
            repository.save(new Author("Marie Kondo", "New York", 30));
            repository.save(new Author("Martin Fowler", "London", 26));
        };
    }
    
    @Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }
}