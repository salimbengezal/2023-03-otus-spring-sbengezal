package ru.otus.homeworks.hw8.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homeworks.hw8.component.impl.CommentFormatter;
import ru.otus.homeworks.hw8.entity.Book;
import ru.otus.homeworks.hw8.entity.Comment;
import ru.otus.homeworks.hw8.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw8.service.BookService;
import ru.otus.homeworks.hw8.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellComponent {

    private static final String GROUP = "Действия с комментариями";

    private final CommentService commentService;

    private final BookService bookService;

    private final CommentFormatter formatter;


    @ShellMethod(key = {"c", "comments"}, value = "Список комментариев книги", group = GROUP)
    public String get(@ShellOption(help = "Book ID") String id) {
        try {
            Book book = bookService.getById(id);
            val comments = commentService.getCommentsByBookId(id);
            return formatter.formatAsBlock(comments, "Комментарии к книге: " + book.getName());
        } catch (EntityNotFoundException e) {
            return "Ошибка: Книга не найдена";
        }
    }

    @ShellMethod(key = {"ac", "add-comment"}, value = "Добавление комментария к книге", group = GROUP)
    public String add(@ShellOption(value = {"m", "message"}, help = "message") String message,
                      @ShellOption(value = {"bid", "book-id"}, help = "Book ID") String id) {
        Comment comment;
        try {
            comment = commentService.add(id, message);
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
        return formatter.formatAsMessage(comment, "добавлен");
    }

    @ShellMethod(key = {"dc", "delete-comment"}, value = "Удаление комментария к книге", group = GROUP)
    public String delete(@ShellOption(help = "Comment ID") String id) {
        Comment comment;
        try {
            comment = commentService.getById(id);
            commentService.deleteById(id);
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
        return formatter.formatAsMessage(comment, "удален");
    }

    @ShellMethod(key = {"uc", "update-comment"}, value = "Обновление комментария к книге", group = GROUP)
    public String update(@ShellOption(value = {"m", "message"}, help = "message") String message,
                         @ShellOption(value = {"id", "comment-id"}, help = "Comment ID") String id) {
        Comment comment;
        try {
            comment = commentService.update(id, message);
        } catch (EntityNotFoundException e) {
            return "Ошибка: " + e.getMessage();
        }
        return formatter.formatAsMessage(comment, "обновлен");
    }

}
