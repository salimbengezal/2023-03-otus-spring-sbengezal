package ru.otus.homeworks.hw10.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public void create(@RequestBody @Valid CommentDtoRequest comment) throws EntityNotFoundException {
        commentService.add(comment);
    }

    @DeleteMapping("/api/comment/{id}")
    public void delete(@PathVariable("id") String id) {
        commentService.deleteById(id);
    }

}
