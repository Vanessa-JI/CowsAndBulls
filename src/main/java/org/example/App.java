package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// kicks off a component scan starting in the annotated class's package
// bootstrap a Spring Boot application without having to worry about configuring
// each of these annotations individually
@SpringBootApplication
public class App {
    public static void main(String ... args) {
        SpringApplication.run(App.class, args);
    }
}
