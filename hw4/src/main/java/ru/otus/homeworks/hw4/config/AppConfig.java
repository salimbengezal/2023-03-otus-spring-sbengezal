package ru.otus.homeworks.hw4.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homeworks.hw4.service.IOService;
import ru.otus.homeworks.hw4.service.impl.IOServiceStreams;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {

    @Bean
    IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }

}
