package ru.otus.homeworks.hw10.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public void doPost(@RequestBody @Valid CommentDtoRequest comment) throws EntityNotFoundException {
        commentService.add(comment);
    }

    @DeleteMapping("/api/comment/{id}")
    public void doDelete(@PathVariable("id") String id) throws EntityNotFoundException {
        commentService.deleteById(id);
    }

}
