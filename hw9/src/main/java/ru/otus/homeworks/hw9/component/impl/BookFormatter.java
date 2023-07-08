package ru.otus.homeworks.hw9.component.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw9.component.MessageDecorator;
import ru.otus.homeworks.hw9.component.ObjectFormatter;
import ru.otus.homeworks.hw9.entity.Book;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class BookFormatter implements ObjectFormatter<Book> {

    private final MessageDecorator decorator;

    @Override
    public String formatAsRow(Book book) {
        return String.format("[%s] %s {%s} (%s) - %s",
                book.getId(),
                book.getName(),
                book.getAuthor().getName(),
                book.getReleaseYear(),
                book.getGenre().getName());
    }

    @Override
    public String formatAsMessage(Book book, String action) {
        return "Книга \"%s\" %s [id=%s]".formatted(book.getName(), action, book.getId());
    }

    @Override
    public String formatAsBlock(List<Book> books, String title) {
        val content = books.isEmpty() ? Stream.of("- пусто -") : books.stream().map(this::formatAsRow);
        return decorator.decorate(title, content);
    }

}
