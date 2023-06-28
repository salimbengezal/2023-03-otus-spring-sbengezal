package ru.otus.homeworks.hw8.component.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw8.component.MessageDecorator;
import ru.otus.homeworks.hw8.component.ObjectFormatter;
import ru.otus.homeworks.hw8.entity.Author;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorFormatter implements ObjectFormatter<Author> {

    private final MessageDecorator decorator;

    @Override
    public String formatAsRow(Author author) {
        return String.format("[%s] %s", author.getId(), author.getName());
    }

    @Override
    public String formatAsMessage(Author author, String action) {
        return "Автор \"%s\" %s [id=%d]".formatted(author.getName(), action, author.getId());
    }

    @Override
    public String formatAsBlock(List<Author> authors, String title) {
        val content = authors.stream().map(this::formatAsRow);
        return decorator.decorate(title, content);
    }
}
