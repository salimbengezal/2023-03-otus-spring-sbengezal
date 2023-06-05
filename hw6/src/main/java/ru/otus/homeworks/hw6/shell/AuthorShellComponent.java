package ru.otus.homeworks.hw6.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homeworks.hw6.component.impl.AuthorFormatter;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw6.service.AuthorService;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellComponent {

    private final AuthorService authorService;

    private final AuthorFormatter formatter;

    @ShellMethod(key = {"a", "authors"}, value = "Show all authors or author by ID", group = "Actions with AUTHORS")
    public String getAuthors(@ShellOption(value = "id", help = "Author ID", defaultValue = NULL) Long id) {
        if (id == null) {
            val authors = authorService.getAll();
            return formatter.formatAsBlock(authors, "Авторы");
        }
        try {
            val book = authorService.getById(id);
            return formatter.formatAsRow(book);
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
    }

}
