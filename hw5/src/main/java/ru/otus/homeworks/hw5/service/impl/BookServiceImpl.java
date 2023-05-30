package ru.otus.homeworks.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw5.dao.BookDao;
import ru.otus.homeworks.hw5.entity.Book;
import ru.otus.homeworks.hw5.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw5.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw5.service.AuthorService;
import ru.otus.homeworks.hw5.service.BookService;
import ru.otus.homeworks.hw5.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book getById(long id) throws EntityNotFoundException {
        return bookDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Книга c [id=%d] не найдена".formatted(id)));
    }

    @Override
    public Book update(long id, String name, Short year, Long authorId, Long genreId) throws EntityNotFoundException {
        Book book = getById(id);
        val author = authorId == null ? book.getAuthor() : authorService.getById(authorId);
        val genre = genreId == null ? book.getGenre() : genreService.getById(genreId);
        val newBook = Book.builder()
                .id(book.getId())
                .name(name == null ? book.getName() : name)
                .releaseYear(year == null ? book.getReleaseYear() : year)
                .author(author)
                .genre(genre)
                .build();
        bookDao.update(newBook);
        return newBook;
    }

    @Override
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
        return bookDao.add(book);
    }

}
