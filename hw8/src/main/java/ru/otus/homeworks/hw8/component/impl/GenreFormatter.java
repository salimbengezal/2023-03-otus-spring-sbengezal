package ru.otus.homeworks.hw8.component.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw8.component.MessageDecorator;
import ru.otus.homeworks.hw8.component.ObjectFormatter;
import ru.otus.homeworks.hw8.entity.Genre;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class GenreFormatter implements ObjectFormatter<Genre> {

    private final MessageDecorator decorator;

    @Override
    public String formatAsRow(Genre genre) {
        return String.format("[%s] %s", genre.getId(), genre.getName());
    }

    @Override
    public String formatAsMessage(Genre genre, String action) {
        return "Жанр \"%s\" %s [id=%s]".formatted(genre.getName(), action, genre.getId());
    }

    @Override
    public String formatAsBlock(List<Genre> genres, String title) {
        val content = genres.isEmpty() ? Stream.of("- пусто -") : genres.stream().map(this::formatAsRow);
        return decorator.decorate(title, content);
    }
}
