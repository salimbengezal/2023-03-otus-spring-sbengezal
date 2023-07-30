package ru.otus.homeworks.hw10.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public List<BookDtoResponse> doGet() {
        return bookService.getAll();
    }

    @PostMapping("/api/book")
    public void doPost(@RequestBody @Valid NewBookDtoRequest book) throws EntityNotFoundException {
        bookService.add(book);
    }

    @DeleteMapping("/api/book/{id}")
    public void doDelete(@PathVariable("id") String id) throws EntityNotFoundException {
        bookService.deleteById(id);
    }

    @PatchMapping("/api/book/{id}")
    public void doPut(@RequestBody @Valid UpdateBookDtoRequest book) throws EntityNotFoundException {
        bookService.update(book);
    }

}
