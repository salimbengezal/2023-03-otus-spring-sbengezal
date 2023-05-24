package ru.otus.homeworks.hw5.component;

import java.util.stream.Stream;

public interface MessageDecorator {

    String decorate(String title, Stream<String> message);

}
