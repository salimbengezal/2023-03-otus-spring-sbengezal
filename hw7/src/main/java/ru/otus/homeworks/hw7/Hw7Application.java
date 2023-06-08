package ru.otus.homeworks.hw7;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

@SpringBootApplication
@EnableJpaRepositories
public class Hw7Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Hw7Application.class, args);
        Console.main(args);
    }

}
