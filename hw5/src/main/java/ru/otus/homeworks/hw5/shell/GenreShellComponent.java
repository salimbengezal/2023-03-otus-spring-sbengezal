package ru.otus.homeworks.hw5.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homeworks.hw5.component.impl.GenreFormatter;
import ru.otus.homeworks.hw5.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellComponent {

    private final GenreService genreService;

    private final GenreFormatter formatter;

    @ShellMethod(key = {"g", "genres"}, value = "Show all genres", group = "Actions with GENRES")
    public String getGenres() {
        val genres = genreService.getAll();
        return formatter.formatAsBlock(genres, "Жанры");
    }
}
