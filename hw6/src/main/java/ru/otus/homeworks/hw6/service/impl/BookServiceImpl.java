package ru.otus.homeworks.hw6.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw6.repositories.BookRepository;
import ru.otus.homeworks.hw6.service.AuthorService;
import ru.otus.homeworks.hw6.service.BookService;
import ru.otus.homeworks.hw6.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    @Transactional
    public void deleteById(long id) throws EntityNotFoundException {
        val book = bookRepository.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга c [id=%d] не найдена".formatted(id)));
        bookRepository.delete(book);
    }

    @Override
    public Book getById(long id) throws EntityNotFoundException {
        return bookRepository.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга c [id=%d] не найдена".formatted(id)));
    }

    @Override
    @Transactional
    public Book update(long id, String name, Short year, Long authorId, Long genreId) throws EntityNotFoundException {
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
    public Book add(String name, Short year, Long authorId, Long genreId)
            throws EntityNotFoundException, AtLeastOneParameterIsNullException {
        if (authorId == null || genreId == null || year == null) {
            throw new AtLeastOneParameterIsNullException();
        }
        val author = authorService.getById(authorId);
        val genre = genreService.getById(genreId);
        val book = Book.builder()
                .name(name)
                .releaseYear(year)
                .author(author)
                .genre(genre)
                .build();
        return bookRepository.save(book);
    }

}
