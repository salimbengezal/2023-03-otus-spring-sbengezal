package ru.otus.homeworks.hw3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homeworks.hw3.service.IOService;
import ru.otus.homeworks.hw3.service.impl.IOServiceStreams;

@Configuration
public class AppConfig {

    @Bean
    IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }

}
