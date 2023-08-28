package ru.otus.homeworks.hw10.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homeworks.hw10.dto.BookDtoResponse;
import ru.otus.homeworks.hw10.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw10.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/book")
    public List<BookDtoResponse> get() {
        return bookService.getAll();
    }

    @PostMapping("/api/book")
    public void create(@RequestBody @Valid NewBookDtoRequest book) throws EntityNotFoundException {
        bookService.add(book);
    }

    @DeleteMapping("/api/book/{id}")
    public void delete(@PathVariable("id") String id) {
        bookService.deleteById(id);
    }

    @PutMapping("/api/book/{id}")
    public void update(@RequestBody @Valid UpdateBookDtoRequest book, @PathVariable("id") String id)
            throws EntityNotFoundException {
        bookService.update(id, book);
    }

}
