package ru.otus.homeworks.hw6.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homeworks.hw6.component.impl.CommentFormatter;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.entity.Comment;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw6.service.BookService;
import ru.otus.homeworks.hw6.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellComponent {

    private final CommentService commentService;

    private final BookService bookService;

    private final CommentFormatter formatter;

    @ShellMethod(key = {"c", "comments"}, value = "Show all comments for book", group = "Actions with COMMENTS")
    public String get(@ShellOption(value = "bid", help = "Book ID") long id) {
        try {
            Book book = bookService.getById(id);
            val comments = commentService.getAllByBookId(id);
            return formatter.formatAsBlock(comments, "Комментарии к книге: " + book.getName());
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
    }

    @ShellMethod(key = {"ac", "add-comment"}, value = "Add new comment to book", group = "Actions with COMMENTS")
    public String add(@ShellOption(value = {"m", "message"}, help = "message") String message,
                      @ShellOption(value = {"bid", "book-id"}, help = "Book ID") long id) {
        try {
            val comment = commentService.add(id, message);
            return formatter.formatAsMessage(comment, "добавлен");
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
    }

    @ShellMethod(key = {"dc", "delete-comment"}, value = "Delete comment by ID", group = "Actions with COMMENTS")
    public String delete(@ShellOption(help = "Comment ID") long id) {
        Comment comment;
        try {
            comment = commentService.getById(id);
            commentService.deleteById(id);
            return formatter.formatAsMessage(comment, "удален");
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
    }

    @ShellMethod(key = {"uc", "update-comment"}, value = "Update comment by ID", group = "Actions with COMMENTS")
    public String update(@ShellOption(value = {"m", "message"}, help = "message") String message,
                         @ShellOption(value = {"id", "comment-id"}, help = "Comment ID") long id) {
        Comment comment;
        try {
            comment = commentService.update(id, message);
        } catch (EntityNotFoundException e) {
            return "Ошибка: " + e.getMessage();
        }
        return formatter.formatAsMessage(comment, "обновлен");
    }

}
