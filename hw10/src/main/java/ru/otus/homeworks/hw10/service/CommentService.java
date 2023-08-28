package ru.otus.homeworks.hw10.service;

import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;

public interface CommentService {

    void deleteById(String id);

    void add(CommentDtoRequest comment) throws EntityNotFoundException;

}
