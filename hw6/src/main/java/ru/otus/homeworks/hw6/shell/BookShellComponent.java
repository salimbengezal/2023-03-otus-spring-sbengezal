package ru.otus.homeworks.hw6.shell;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homeworks.hw6.component.impl.BookFormatter;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw6.service.BookService;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@RequiredArgsConstructor
public class BookShellComponent {

    private final BookService bookService;

    private final BookFormatter formatter;

    @ShellMethod(key = {"b", "books"}, value = "Show all books or book by ID", group = "Actions with BOOKS")
    public String getBooks(@ShellOption(value = "id", help = "Book ID", defaultValue = NULL) Long id) {
        if (id == null) {
            val books = bookService.getAll();
            return formatter.formatAsBlock(books, "Книги");
        }
        try {
            val book = bookService.getById(id);
            return formatter.formatAsRow(book);
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
    }

    @ShellMethod(key = {"ab", "add-book"}, value = "Add new book", group = "Actions with BOOKS")
    public String addBook(@ShellOption(value = {"n", "name"}, help = "Book name") String name,
                          @ShellOption(value = {"y", "year"}, help = "Year of publication of the book") Short year,
                          @ShellOption(value = {"aid", "author-id"}, help = "Author ID") Long authorId,
                          @ShellOption(value = {"gid", "genre-id"}, help = "Genre ID") Long genreId) {
        Book book;
        try {
            book = bookService.add(name, year, authorId, genreId);
        } catch (EntityNotFoundException | AtLeastOneParameterIsNullException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
        return formatter.formatAsMessage(book, "добавлена");
    }

    @ShellMethod(key = {"db", "delete-book"}, value = "Delete book by ID", group = "Actions with BOOKS")
    public String deleteBookById(@ShellOption(help = "Book ID") long id) {
        try {
            Book book = bookService.getById(id);
            bookService.deleteById(id);
            return formatter.formatAsMessage(book, "удалена");
        } catch (EntityNotFoundException e) {
            return "Ошибка: %s".formatted(e.getMessage());
        }
    }

    @ShellMethod(key = {"ub", "update-book"}, value = "Update book by ID", group = "Actions with BOOKS")
    public String updateBookById(@ShellOption(help = "Book ID") long id,
                                 @ShellOption(value = {"n", "name"}, help = "Book name", defaultValue = NULL)
                                 String name,
                                 @ShellOption(value = {"y", "year"}, help = "Year of publication", defaultValue = NULL)
                                 Short year,
                                 @ShellOption(value = {"aid", "author-id"}, help = "Author ID", defaultValue = NULL)
                                 Long authorId,
                                 @ShellOption(value = {"gid", "genre-id"}, help = "Genre ID", defaultValue = NULL)
                                 Long genreId) {
        if (name == null && year == null && authorId == null && genreId == null) {
            return "Ошибка: Не указан хотя бы 1 поле для изменения [id=%s]".formatted(id);
        }
        Book book;
        try {
            book = bookService.update(id, name, year, authorId, genreId);
        } catch (EntityNotFoundException e) {
            return "Ошибка: " + e.getMessage();
        }
        return formatter.formatAsRow(book);
    }
}
