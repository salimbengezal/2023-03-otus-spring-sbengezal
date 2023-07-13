package ru.otus.homeworks.hw9.controllers;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homeworks.hw9.dto.NewCommentDtoRequest;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw9.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/book/{id}/add-comment")
    public String createComment(@PathVariable("id") String bookId, String message) throws EntityNotFoundException {
        val newComment = new NewCommentDtoRequest(message, bookId);
        commentService.add(newComment);
        return "redirect:/book/" + bookId;
    }

    @GetMapping("/book/{id}/delete-comment")
    public String removeComment(@PathVariable("id") String bookId, @RequestParam("id") String commentId) throws EntityNotFoundException {
        commentService.deleteById(commentId);
        return "redirect:/book/" + bookId;
    }

}
