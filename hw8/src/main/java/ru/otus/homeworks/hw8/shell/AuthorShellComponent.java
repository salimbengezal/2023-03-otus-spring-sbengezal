package ru.otus.homeworks.hw8.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homeworks.hw8.component.impl.AuthorFormatter;
import ru.otus.homeworks.hw8.service.AuthorService;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellComponent {

    private final AuthorService authorService;

    private final AuthorFormatter formatter;

    @ShellMethod(key = {"a", "authors"}, value = "Список авторов (с поиском)", group = "Действия с авторами")
    public String get(@ShellOption(help = "Совпадающее название", defaultValue = NULL) String name) {
        if (name == null) {
            val authors = authorService.getAll();
            return formatter.formatAsBlock(authors, "Авторы");
        }
        val authors = authorService.getAllByNameContains(name);
        return formatter.formatAsBlock(authors, "Авторы [поиск]");
    }
}
