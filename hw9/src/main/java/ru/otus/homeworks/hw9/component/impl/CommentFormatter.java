package ru.otus.homeworks.hw9.component.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw9.component.MessageDecorator;
import ru.otus.homeworks.hw9.component.ObjectFormatter;
import ru.otus.homeworks.hw9.entity.Comment;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CommentFormatter implements ObjectFormatter<Comment> {

    private final MessageDecorator decorator;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String formatAsRow(Comment comment) {
        return String.format("[%s] (%s) %s",
                comment.getId(),
                dtf.format(comment.getUpdateOn()),
                comment.getMessage());
    }

    @Override
    public String formatAsMessage(Comment comment, String action) {
        return "Комментарий [id=%s] %s".formatted(comment.getId(), action);

    }

    @Override
    public String formatAsBlock(List<Comment> comments, String title) {
        val content = comments.isEmpty() ? Stream.of("- пусто -") : comments.stream().map(this::formatAsRow);
        return decorator.decorate(title, content);
    }

}
