package ru.otus.homeworks.hw9.controllers;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homeworks.hw9.dto.CommentDtoRequest;
import ru.otus.homeworks.hw9.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw9.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw9.service.AuthorService;
import ru.otus.homeworks.hw9.service.BookService;
import ru.otus.homeworks.hw9.service.CommentService;
import ru.otus.homeworks.hw9.service.GenreService;

@Controller
@RequiredArgsConstructor
public class BookPagesController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final CommentService commentService;

    @GetMapping("/book")
    public String list(Model model) {
        val books = bookService.getAll();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/book/new")
    public String create(Model model) {
        val genres = genreService.getAll();
        val authors = authorService.getAll();
        model.addAttribute("book", new NewBookDtoRequest());
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "book/new";
    }

    @GetMapping("/book/{id}")
    public String details(Model model, @PathVariable("id") String id) throws EntityNotFoundException {
        val book = bookService.getById(id);
        val comments = commentService.getCommentsByBookId(book.id());
        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new CommentDtoRequest());
        return "book/details";
    }

    @GetMapping("/book/edit")
    public String edit(Model model, @RequestParam("id") String id) throws EntityNotFoundException {
        val book = bookService.getById(id);
        val validatedBook = new UpdateBookDtoRequest(book.id(), book.name(), book.releaseYear(), book.author().id(),
                book.genre().id());
        val genres = genreService.getAll();
        val authors = authorService.getAll();
        model.addAttribute("book", validatedBook);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "book/edit";
    }

}
