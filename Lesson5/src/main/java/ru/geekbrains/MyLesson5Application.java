package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.repositories.StudentRepository;
import ru.geekbrains.services.StudentService;

@SpringBootApplication
public class MyLesson5Application {


    public static void main(String[] args) {
        SpringApplication.run(MyLesson5Application.class,args);

    }
}
