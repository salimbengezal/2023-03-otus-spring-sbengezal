package ru.otus.homeworks.hw8.mongock.changelog;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.otus.homeworks.hw8.entity.Author;
import ru.otus.homeworks.hw8.entity.Book;
import ru.otus.homeworks.hw8.entity.Comment;
import ru.otus.homeworks.hw8.entity.Genre;
import ru.otus.homeworks.hw8.repositories.AuthorRepository;
import ru.otus.homeworks.hw8.repositories.BookRepository;
import ru.otus.homeworks.hw8.repositories.CommentRepository;
import ru.otus.homeworks.hw8.repositories.GenreRepository;

import java.time.LocalDateTime;
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
                .map(genre -> Genre.builder().name(genre).build())
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
                .map(author -> Author.builder().name(author).build())
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
        return Stream.of(
                Book.builder()
                        .name("Гарри Поттер и философский камень")
                        .releaseYear((short) 1997)
                        .author(getAuthor(authors, "Джоан Роулинг"))
                        .genre(getGenre(genres, "фэнтези"))
                        .build(),
                Book.builder()
                        .name("Триумфальная арка")
                        .releaseYear((short) 1945)
                        .author(getAuthor(authors, "Эрих Мария Ремарк"))
                        .genre(getGenre(genres, "роман"))
                        .build(),
                Book.builder()
                        .name("Мастер и Маргарита")
                        .releaseYear((short) 1967)
                        .author(getAuthor(authors, "Михаил Булгаков")).
                        genre(getGenre(genres, "фантастика"))
                        .build(),
                Book.builder()
                        .name("1984")
                        .releaseYear((short) 1949)
                        .author(getAuthor(authors, "Джордж Оруэлл"))
                        .genre(getGenre(genres, "научная фантастика"))
                        .build(),
                Book.builder()
                        .name("Война и мир")
                        .releaseYear((short) 1869)
                        .author(getAuthor(authors, "Лев Толстой"))
                        .genre(getGenre(genres, "исторический роман"))
                        .build(),
                Book.builder()
                        .name("Анна Каренина")
                        .releaseYear((short) 1877)
                        .author(getAuthor(authors, "Лев Толстой"))
                        .genre(getGenre(genres, "роман"))
                        .build(),
                Book.builder()
                        .name("Убить пересмешника")
                        .releaseYear((short) 1960)
                        .author(getAuthor(authors, "Харпер Ли"))
                        .genre(getGenre(genres, "роман"))
                        .build(),
                Book.builder()
                        .name("Маленький принц")
                        .releaseYear((short) 1943)
                        .author(getAuthor(authors, "Антуан де Сент-Экзюпери"))
                        .genre(getGenre(genres, "философская сказка"))
                        .build(),
                Book.builder()
                        .name("О дивный новый мир")
                        .releaseYear((short) 1932)
                        .author(getAuthor(authors, "Олдос Хаксли"))
                        .genre(getGenre(genres, "научная фантастика"))
                        .build(),
                Book.builder()
                        .name("Шантарам")
                        .releaseYear((short) 2003)
                        .author(getAuthor(authors, "Грегори Дэвид Робертс"))
                        .genre(getGenre(genres, "автобиографический роман"))
                        .build()
        ).toList();
    }

    private List<Comment> getComments(List<Book> books) {
        return Stream.of(
                Comment.builder()
                        .book(getBook(books, "Гарри Поттер и философский камень"))
                        .message("Мне больше фильм понравился, чем книга")
                        .updateOn(LocalDateTime.now())
                        .build(),
                Comment.builder()
                        .book(getBook(books, "Гарри Поттер и философский камень"))
                        .message("Отличная книга")
                        .updateOn(LocalDateTime.now())
                        .build(),
                Comment.builder()
                        .book(getBook(books, "Триумфальная арка"))
                        .message("Добавил в себе список для чтения")
                        .updateOn(LocalDateTime.now())
                        .build(),
                Comment.builder()
                        .book(getBook(books, "Война и мир"))
                        .message("Слишком много читать, не зацепило")
                        .updateOn(LocalDateTime.now())
                        .build()
        ).toList();
    }
}
