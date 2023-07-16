package ru.otus.homeworks.hw9.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homeworks.hw9.dto.CommentDtoRequest;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw9.service.BookService;
import ru.otus.homeworks.hw9.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final BookService bookService;

    private final CommentService commentService;

    @PostMapping("/book/{id}/add-comment")
    public String createComment(@PathVariable("id") String bookId,
                                @Valid @ModelAttribute("comment") CommentDtoRequest comment,
                                BindingResult bindingResult, Model model) throws EntityNotFoundException {

        if (bindingResult.hasErrors()) {
            val book = bookService.getById(bookId);
            val comments = commentService.getCommentsByBookId(book.id());
            model.addAttribute("book", book);
            model.addAttribute("comments", comments);
            model.addAttribute("comment", comment);
            return "book/details";
        }

        comment.setBookId(bookId);
        commentService.add(comment);
        return "redirect:/book/" + bookId;
    }

    @GetMapping("/book/{id}/delete-comment")
    public String removeComment(@PathVariable("id") String bookId, @RequestParam("id") String commentId)
            throws EntityNotFoundException {
        commentService.deleteById(commentId);
        return "redirect:/book/" + bookId;
    }

}
