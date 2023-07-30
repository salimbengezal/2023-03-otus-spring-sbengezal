package ru.otus.homeworks.hw10.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.dto.DeleteCommentDtoRequest;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.service.BookService;
import ru.otus.homeworks.hw10.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final BookService bookService;

    private final CommentService commentService;

    @PostMapping(value = "/comment", params = "create")
    public String create(@Valid @ModelAttribute("comment") CommentDtoRequest comment,
                         BindingResult bindingResult, Model model) throws EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            val book = bookService.getById(comment.getBookId());
            val comments = commentService.getCommentsByBookId(book.id());
            model.addAttribute("book", book);
            model.addAttribute("comments", comments);
            model.addAttribute("comment", comment);
            return "book/details";
        }
        commentService.add(comment);
        return "redirect:/book/" + comment.getBookId();
    }

    @PostMapping(value = "/comment", params = "delete")
    public String delete(@ModelAttribute("comment") DeleteCommentDtoRequest commentDto)
            throws EntityNotFoundException {
        commentService.deleteById(commentDto.commentId());
        return "redirect:/book/" + commentDto.bookId();
    }

}
