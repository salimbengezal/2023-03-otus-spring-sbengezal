package ru.otus.homeworks.hw10.service;

import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.dto.CommentDtoResponse;
import ru.otus.homeworks.hw10.exceptions.EntityNotFoundException;

import java.util.List;

public interface CommentService {

    List<CommentDtoResponse> getCommentsByBookId(String id);

    void deleteById(String id) throws EntityNotFoundException;

    void add(CommentDtoRequest comment) throws EntityNotFoundException;

}
