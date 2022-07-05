package org.example.Currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.example.Currency.api")
public class ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);

    }
}
