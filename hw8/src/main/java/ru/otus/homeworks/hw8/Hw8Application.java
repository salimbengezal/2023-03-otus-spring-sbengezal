package ru.otus.homeworks.hw8;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw8Application {

    public static void main(String[] args) {
        val context = SpringApplication.run(ru.otus.homeworks.hw8.Hw8Application.class, args);
        context.getBean("mongo");
    }

}
