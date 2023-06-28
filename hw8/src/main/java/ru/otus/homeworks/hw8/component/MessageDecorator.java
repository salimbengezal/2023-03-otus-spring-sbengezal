package ru.otus.homeworks.hw8.component;

import java.util.stream.Stream;

public interface MessageDecorator {

    String decorate(String title, Stream<String> message);

}
