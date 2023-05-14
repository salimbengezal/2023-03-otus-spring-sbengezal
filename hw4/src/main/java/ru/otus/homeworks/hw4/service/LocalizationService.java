package ru.otus.homeworks.hw4.service;

public interface LocalizationService {

    String getMessage(String key);

    String getMessage(String key, String... args);

}
