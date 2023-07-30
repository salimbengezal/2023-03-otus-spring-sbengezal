package ru.otus.homeworks.hw10.mongock.changelog;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.otus.homeworks.hw10.entity.Author;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.entity.Comment;
import ru.otus.homeworks.hw10.entity.Genre;
import ru.otus.homeworks.hw10.repository.AuthorRepository;
import ru.otus.homeworks.hw10.repository.BookRepository;
import ru.otus.homeworks.hw10.repository.CommentRepository;
import ru.otus.homeworks.hw10.repository.GenreRepository;

import java.util.List;
import java.util.stream.Stream;

@ChangeUnit(id = "v1", order = "1", author = "Salim Bengezal")
@RequiredArgsConstructor
public class V1ChangeUnit {

    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    @Execution
    public void execution() {
        val genres = getGenres();
        genreRepository.saveAll(genres);
        val authors = getAuthors();
        authorRepository.saveAll(authors);
        val books = getBooks(genres, authors);
        bookRepository.saveAll(books);
        val comments = getComments(books);
        commentRepository.saveAll(comments);
    }

    @RollbackExecution
    public void rollbackExecution() {
        genreRepository.deleteAll();
        authorRepository.deleteAll();
        bookRepository.deleteAll();
        commentRepository.deleteAll();
    }

    private List<Genre> getGenres() {
        return Stream.of("фэнтези",
                        "роман",
                        "фантастика",
                        "сказка",
                        "исторический роман",
                        "научная фантастика",
                        "философская сказка",
                        "автобиографический роман")
                .map(name -> new Genre(null, name))
                .toList();
    }

    private List<Author> getAuthors() {
        return Stream.of("Джоан Роулинг",
                        "Эрих Мария Ремарк",
                        "Михаил Булгаков",
                        "Джордж Оруэлл",
                        "Лев Толстой",
                        "Харпер Ли",
                        "Антуан де Сент-Экзюпери",
                        "Олдос Хаксли",
                        "Грегори Дэвид Робертс")
                .map(name -> new Author(null, name))
                .toList();
    }

    private Genre getGenre(List<Genre> genres, String name) {
        return genres.stream().filter(g -> g.getName().equals(name)).findFirst().orElseThrow();
    }

    private Author getAuthor(List<Author> authors, String name) {
        return authors.stream().filter(g -> g.getName().equals(name)).findFirst().orElseThrow();
    }

    private Book getBook(List<Book> books, String name) {
        return books.stream().filter(g -> g.getName().equals(name)).findFirst().orElseThrow();
    }

    private List<Book> getBooks(List<Genre> genres, List<Author> authors) {
        return List.of(new Book("Гарри Поттер и философский камень", (short) 1997, getAuthor(authors,
                        "Джоан Роулинг"), getGenre(genres, "фэнтези")),
                new Book("Триумфальная арка", (short) 1945, getAuthor(authors, "Эрих Мария Ремарк"),
                        getGenre(genres, "роман")),
                new Book("Мастер и Маргарита", (short) 1967, getAuthor(authors, "Михаил Булгаков"),
                        getGenre(genres, "фантастика")),
                new Book("1984", (short) 1949, getAuthor(authors, "Джордж Оруэлл"),
                        getGenre(genres, "научная фантастика")),
                new Book("Война и мир", (short) 1869, getAuthor(authors, "Лев Толстой"),
                        getGenre(genres, "исторический роман")),
                new Book("Анна Каренина", (short) 1877, getAuthor(authors, "Лев Толстой"),
                        getGenre(genres, "роман")),
                new Book("Убить пересмешника", (short) 1960, getAuthor(authors, "Харпер Ли"),
                        getGenre(genres, "роман")),
                new Book("Маленький принц", (short) 1943,
                        getAuthor(authors, "Антуан де Сент-Экзюпери"),
                        getGenre(genres, "философская сказка")),
                new Book("О дивный новый мир", (short) 1932, getAuthor(authors, "Олдос Хаксли"),
                        getGenre(genres, "научная фантастика")),
                new Book("Шантарам", (short) 2003, getAuthor(authors, "Грегори Дэвид Робертс"),
                        getGenre(genres, "автобиографический роман"))
        );
    }

    private List<Comment> getComments(List<Book> books) {
        return List.of(
                new Comment("Мне больше фильм понравился, чем книга",
                        getBook(books, "Гарри Поттер и философский камень")),
                new Comment("Отличная книга", getBook(books, "Гарри Поттер и философский камень")),
                new Comment("Добавил в себе список для чтения", getBook(books, "Триумфальная арка")),
                new Comment("Слишком много читать, не зацепило", getBook(books, "Война и мир"))
        );
    }
}
