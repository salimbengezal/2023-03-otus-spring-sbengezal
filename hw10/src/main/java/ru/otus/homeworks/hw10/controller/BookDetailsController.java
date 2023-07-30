package ru.otus.homeworks.hw10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homeworks.hw10.dto.BookDetailsDtoResponse;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.service.BookDetailsService;

@RestController
@RequiredArgsConstructor
public class BookDetailsController {

    private final BookDetailsService bookService;

    @GetMapping("/api/book/{id}")
    public BookDetailsDtoResponse doGet(@PathVariable("id") String id) throws EntityNotFoundException {
        return bookService.getById(id);
    }

}
