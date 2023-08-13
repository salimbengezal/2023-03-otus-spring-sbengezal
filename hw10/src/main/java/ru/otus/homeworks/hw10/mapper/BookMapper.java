package ru.otus.homeworks.hw10.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw10.dto.AuthorDtoResponse;
import ru.otus.homeworks.hw10.dto.BookDetailsDtoResponse;
import ru.otus.homeworks.hw10.dto.BookDtoResponse;
import ru.otus.homeworks.hw10.dto.CommentDtoResponse;
import ru.otus.homeworks.hw10.dto.GenreDtoResponse;
import ru.otus.homeworks.hw10.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw10.entity.Author;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.entity.Comment;
import ru.otus.homeworks.hw10.entity.Genre;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final AuthorMapper authorMapper;

    private final GenreMapper genreMapper;

    public BookDetailsDtoResponse toDto(Book book, List<Comment> comments) {
        val genre = book.getGenre();
        val author = book.getAuthor();
        val authorDto = new AuthorDtoResponse(author.getId(), author.getName());
        val genreDto = new GenreDtoResponse(genre.getId(), genre.getName());
        val bookDto = new BookDtoResponse(book.getId(), book.getName(), book.getReleaseYear(), authorDto, genreDto);
        val commentsDto = comments.stream()
                .map(comment -> new CommentDtoResponse(comment.getId(), comment.getMessage(), comment.getUpdateOn()))
                .toList();
        return new BookDetailsDtoResponse(bookDto, commentsDto);
    }

    public Book toEntity(NewBookDtoRequest bookDto, Author author, Genre genre) {
        return new Book(bookDto.getName(), bookDto.getReleaseYear(), author, genre);
    }

    public BookDtoResponse toDto(Book book) {
        val genreDto = genreMapper.toDto(book.getGenre());
        val authorDto = authorMapper.toDto(book.getAuthor());
        return new BookDtoResponse(book.getId(), book.getName(), book.getReleaseYear(), authorDto, genreDto);
    }

    public List<BookDtoResponse> toDto(List<Book> books) {
        return books.stream().map(this::toDto).toList();
    }
}
