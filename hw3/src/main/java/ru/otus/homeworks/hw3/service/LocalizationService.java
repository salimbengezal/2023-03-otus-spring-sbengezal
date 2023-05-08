package ru.otus.homeworks.hw3.service;

public interface LocalizationService {

    String getMessage(String key);

    String getMessage(String key, String... args);

}
