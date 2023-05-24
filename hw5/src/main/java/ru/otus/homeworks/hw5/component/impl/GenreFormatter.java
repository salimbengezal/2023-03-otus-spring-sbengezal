package ru.otus.homeworks.hw5.component.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw5.component.MessageDecorator;
import ru.otus.homeworks.hw5.component.ObjectFormatter;
import ru.otus.homeworks.hw5.entity.Genre;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreFormatter implements ObjectFormatter<Genre> {

    private final MessageDecorator decorator;

    @Override
    public String formatAsRow(Genre genre) {
        return String.format("[%s] %s", genre.getId(), genre.getName());
    }

    @Override
    public String formatAsBlock(List<Genre> genres, String title) {
        val content = genres.stream().map(this::formatAsRow);
        return decorator.decorate(title, content);
    }

    @Override
    public String formatAsMessage(Genre genre, String action) {
        return "Жанр \"%s\" %s [id=%d]".formatted(genre.getName(), action, genre.getId());
    }

}
