package ru.otus.homeworks.hw8.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homeworks.hw8.component.impl.GenreFormatter;
import ru.otus.homeworks.hw8.service.GenreService;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellComponent {

    private final GenreService genreService;

    private final GenreFormatter formatter;

    @ShellMethod(key = {"g", "genres"}, value = "Список жанров (с поиском)", group = "Действия с жанрами")
    public String get(@ShellOption(help = "Совпадающее название", defaultValue = NULL) String name) {
        if (name == null) {
            val genres = genreService.getAll();
            return formatter.formatAsBlock(genres, "Жанры");
        }
        val genres = genreService.getAllByNameContains(name);
        return formatter.formatAsBlock(genres, "Жанры [поиск]");
    }
}
