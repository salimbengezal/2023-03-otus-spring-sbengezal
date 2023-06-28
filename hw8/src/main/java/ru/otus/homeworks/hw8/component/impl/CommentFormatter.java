package ru.otus.homeworks.hw8.component.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw8.component.MessageDecorator;
import ru.otus.homeworks.hw8.component.ObjectFormatter;
import ru.otus.homeworks.hw8.entity.Comment;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentFormatter implements ObjectFormatter<Comment> {

    private final MessageDecorator decorator;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String formatAsRow(Comment comment) {
        return String.format("[%s] (%s) %s",
                comment.getId(),
                dtf.format(comment.getUpdateOn().toLocalDateTime()),
                comment.getMessage());
    }

    @Override
    public String formatAsMessage(Comment comment, String action) {
        return "Комментарий [id=%d] %s".formatted(comment.getId(), action);

    }

    @Override
    public String formatAsBlock(List<Comment> comments, String title) {
        val content = comments.stream().map(this::formatAsRow);
        return decorator.decorate(title, content);
    }
}
