package ru.otus.homeworks.hw10.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeworks.hw10.dto.BookDtoResponse;
import ru.otus.homeworks.hw10.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw10.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw10.entity.Author;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.entity.Genre;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.mapper.BookMapper;
import ru.otus.homeworks.hw10.repository.AuthorRepository;
import ru.otus.homeworks.hw10.repository.BookRepository;
import ru.otus.homeworks.hw10.repository.GenreRepository;
import ru.otus.homeworks.hw10.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookMapper mapper;

    @Override
    public List<BookDtoResponse> getAll() {
        return bookRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(String id) throws EntityNotFoundException {
        bookRepository.deleteById(id);
    }

    private Author getAuthor(String id) throws EntityNotFoundException {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Автор не найден"));
    }

    private Genre getGenre(String id) throws EntityNotFoundException {
        return genreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Жанр не найден"));
    }

    private Book getBook(String id) throws EntityNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
    }

    @Override
    @Transactional
    public void update(String id, UpdateBookDtoRequest bookDto) throws EntityNotFoundException {
        Book book = getBook(id);
        if (bookDto.authorId() != null) {
            val newAuthor = getAuthor(bookDto.authorId());
            book.setAuthor(newAuthor);
        }
        if (bookDto.genreId() != null) {
            val newGenre = getGenre(bookDto.genreId());
            book.setGenre(newGenre);
        }
        if (bookDto.name() != null) {
            book.setName(bookDto.name());
        }
        if (bookDto.releaseYear() != null) {
            book.setReleaseYear(bookDto.releaseYear());
        }
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void add(NewBookDtoRequest bookDto) throws EntityNotFoundException {
        val author = getAuthor(bookDto.getAuthorId());
        val genre = getGenre(bookDto.getGenreId());
        val book = mapper.toEntity(bookDto, author, genre);
        bookRepository.save(book);
    }

}
