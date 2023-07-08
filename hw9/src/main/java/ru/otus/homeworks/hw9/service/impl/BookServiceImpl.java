package ru.otus.homeworks.hw9.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeworks.hw9.entity.Book;
import ru.otus.homeworks.hw9.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw9.repositories.BookRepository;
import ru.otus.homeworks.hw9.service.AuthorService;
import ru.otus.homeworks.hw9.service.BookService;
import ru.otus.homeworks.hw9.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book deleteById(String id) throws EntityNotFoundException {
        val book = getById(id);
        bookRepository.delete(book);
        return book;
    }

    @Override
    public Book getById(String id) throws EntityNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Книга не найдена"));
    }

    @Override
    @Transactional
    public Book update(String id, String name, Short year, String authorId, String genreId)
            throws EntityNotFoundException {
        Book book = getById(id);
        if (authorId != null) {
            val newAuthor = authorService.getById(authorId);
            book.setAuthor(newAuthor);
        }
        if (genreId != null) {
            val newGenre = genreService.getById(genreId);
            book.setGenre(newGenre);
        }
        if (name != null) {
            book.setName(name);
        }
        if (year != null) {
            book.setReleaseYear(year);
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book add(String name, Short year, String authorId, String genreId)
            throws AtLeastOneParameterIsNullException, EntityNotFoundException {
        if (authorId == null || genreId == null || year == null) {
            throw new AtLeastOneParameterIsNullException();
        }
        val author = authorService.getById(authorId);
        val genre = genreService.getById(genreId);
        val book = new Book(name, year, author, genre);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllByNameContains(String name) {
        return bookRepository.findByNameContainingIgnoreCase(name);
    }

}
