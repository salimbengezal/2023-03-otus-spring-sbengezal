package ru.otus.homeworks.hw9.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeworks.hw9.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw9.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw9.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    public String create(@RequestBody NewBookDtoRequest book) throws EntityNotFoundException {
        bookService.add(book);
        return "redirect:/book";
    }

    @GetMapping("/book/delete")
    public String delete(@RequestParam("id") String id) throws EntityNotFoundException {
        bookService.deleteById(id);
        return "redirect:/book";
    }

    @PostMapping("/book/update")
    public String update(@RequestBody UpdateBookDtoRequest book) throws EntityNotFoundException {
        bookService.update(book);
        return "redirect:/book";
    }

}
