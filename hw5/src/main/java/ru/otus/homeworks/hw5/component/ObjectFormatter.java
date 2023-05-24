package ru.otus.homeworks.hw5.component;

import java.util.List;

public interface ObjectFormatter<T> {

    String formatAsRow(T object);

    String formatAsMessage(T object, String action);

    String formatAsBlock(List<T> objects, String title);

}
