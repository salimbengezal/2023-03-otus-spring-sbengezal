package ru.otus.homeworks.hw6.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homeworks.hw6.component.impl.GenreFormatter;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw6.service.GenreService;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellComponent {

    private final GenreService genreService;

    private final GenreFormatter formatter;

    @ShellMethod(key = {"g", "genres"}, value = "Show all genres or genre by ID", group = "Actions with GENRES")
    public String getGenres(@ShellOption(value = "id", help = "Genre ID", defaultValue = NULL) Long id) {
        if (id == null) {
            val genres = genreService.getAll();
            return formatter.formatAsBlock(genres, "Жанры");
        }
        try {
            val genre = genreService.getById(id);
            return formatter.formatAsRow(genre);
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
    }
}
