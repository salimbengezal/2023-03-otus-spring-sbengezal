package ru.otus.homeworks.hw9.component;

import java.util.stream.Stream;

public interface MessageDecorator {

    String decorate(String title, Stream<String> message);

}
