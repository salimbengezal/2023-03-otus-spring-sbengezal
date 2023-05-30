package ru.otus.homeworks.hw5.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homeworks.hw5.component.impl.AuthorFormatter;
import ru.otus.homeworks.hw5.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellCommands {

    private final AuthorService authorService;

    private final AuthorFormatter formatter;

    @ShellMethod(key = {"a", "authors"}, value = "Show all authors", group = "Actions with AUTHORS")
    public String getAuthors() {
        val authors = authorService.getAll();
        return formatter.formatAsBlock(authors, "Авторы");
    }

}
