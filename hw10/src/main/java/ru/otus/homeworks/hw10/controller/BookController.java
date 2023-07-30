package ru.otus.homeworks.hw10.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homeworks.hw10.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw10.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.service.AuthorService;
import ru.otus.homeworks.hw10.service.BookService;
import ru.otus.homeworks.hw10.service.GenreService;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @PostMapping(value = "/book", params = "create")
    public String create(@Valid @ModelAttribute("book") NewBookDtoRequest book,
                         BindingResult bindingResult, Model model) throws EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            val genres = genreService.getAll();
            val authors = authorService.getAll();
            model.addAttribute("genres", genres);
            model.addAttribute("authors", authors);
            model.addAttribute("book", book);
            return "book/new";
        }
        bookService.add(book);
        return "redirect:/book";
    }

    @PostMapping(value = "/book", params = "delete")
    public String delete(@RequestParam("id") String id) throws EntityNotFoundException {
        bookService.deleteById(id);
        return "redirect:/book";
    }

    @PostMapping(value = "/book", params = "update")
    public String update(@ModelAttribute("book") @Valid UpdateBookDtoRequest book,
                         BindingResult bindingResult, Model model) throws EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            val genres = genreService.getAll();
            val authors = authorService.getAll();
            model.addAttribute("genres", genres);
            model.addAttribute("authors", authors);
            model.addAttribute("book", book);
            return "book/edit";
        }
        bookService.update(book);
        return "redirect:/book";
    }

}
