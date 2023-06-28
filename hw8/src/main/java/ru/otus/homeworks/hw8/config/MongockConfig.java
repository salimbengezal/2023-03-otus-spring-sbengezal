package ru.otus.homeworks.hw8.config;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories(basePackages = "ru.otus.homeworks.hw8.repositories")
@Configuration
public class MongockConfig {
}
