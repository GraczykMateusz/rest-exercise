package dev.graczykmateusz.restexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RestExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestExerciseApplication.class, args);
    }
}
