package ru.otus.homeworks.hw8.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homeworks.hw8.component.impl.BookFormatter;
import ru.otus.homeworks.hw8.entity.Book;
import ru.otus.homeworks.hw8.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw8.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw8.service.BookService;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@RequiredArgsConstructor
public class BookShellComponent {

    private static final String GROUP = "Действия с книгами";

    private final BookService bookService;

    private final BookFormatter formatter;


    @ShellMethod(key = {"b", "books"}, value = "Список книг (с поиском)", group = GROUP)
    public String get(@ShellOption(help = "Совпадающее название", defaultValue = NULL) String name) {
        if (name == null) {
            val books = bookService.getAll();
            return formatter.formatAsBlock(books, "Книги");
        }
        val books = bookService.getAllByNameContains(name);
        return formatter.formatAsBlock(books, "Книги [поиск]");
    }

    @ShellMethod(key = {"ab", "add-book"}, value = "Добавление книги", group = GROUP)
    public String add(@ShellOption(value = {"n", "name"}, help = "Book name") String name,
                      @ShellOption(value = {"y", "year"}, help = "Year of publication of the book") Short year,
                      @ShellOption(value = {"aid", "author-id"}, help = "Author ID") String authorId,
                      @ShellOption(value = {"gid", "genre-id"}, help = "Genre ID") String genreId) {
        Book book;
        try {
            book = bookService.add(name, year, authorId, genreId);
        } catch (EntityNotFoundException | AtLeastOneParameterIsNullException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
        return formatter.formatAsMessage(book, "добавлена");
    }

    @ShellMethod(key = {"db", "delete-book"}, value = "Удаление книги", group = GROUP)
    public String delete(@ShellOption(help = "Book ID") String id) {
        Book book;
        try {
            book = bookService.deleteById(id);
        } catch (EntityNotFoundException e) {
            return "Ошибка: Книга c [id=%s] не найдена".formatted(id);
        }
        return formatter.formatAsMessage(book, "удалена");
    }

    @ShellMethod(key = {"ub", "update-book"}, value = "Обновление книги", group = GROUP)
    public String update(@ShellOption(help = "Book ID") String id,
                         @ShellOption(value = {"n", "name"}, help = "Book name", defaultValue = NULL) String name,
                         @ShellOption(value = {"y", "year"}, help = "Release Year", defaultValue = NULL) Short year,
                         @ShellOption(value = "aid", help = "Author ID", defaultValue = NULL) String authorId,
                         @ShellOption(value = "gid", help = "Genre ID", defaultValue = NULL) String genreId) {
        if (name == null && year == null && authorId == null && genreId == null) {
            return "Ошибка: Не указано хотя бы 1 поле для изменения";
        }
        Book book;
        try {
            book = bookService.update(id, name, year, authorId, genreId);
        } catch (EntityNotFoundException e) {
            return "Ошибка: Автор не найден";
        }
        return formatter.formatAsRow(book);
    }

}
