package ru.otus.homeworks.hw4.service;

public interface IOLocalizationService {

    void showMessageByKey(boolean inline, String key);

    void showText(String message);

    void showText(boolean inline, String message);

    String readString();

    int readInt();

}
