package ru.otus.homeworks.hw6;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Hw6Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Hw6Application.class, args);
        Console.main(args);
    }

}
