package ru.otus.homeworks.hw9.component.impl;

import lombok.val;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw9.component.MessageDecorator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MessageDecoratorImpl implements MessageDecorator {

    @Override
    public String decorate(String title, Stream<String> content) {
        val headerString = String.format("--- %s ---", title);
        val header = Stream.of(headerString);
        val footer = Stream.of("-".repeat(headerString.length()));
        return Stream.concat(Stream.concat(header, content), footer)
                .collect(Collectors.joining("\n"));
    }
}
