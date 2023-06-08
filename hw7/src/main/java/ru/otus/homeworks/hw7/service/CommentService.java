package ru.otus.homeworks.hw7.service;

import ru.otus.homeworks.hw7.entity.Comment;
import ru.otus.homeworks.hw7.exceptions.EntityNotFoundException;

import java.util.List;

public interface CommentService {

    List<Comment> getAllByBookId(long id) throws EntityNotFoundException;

    void deleteById(long id) throws EntityNotFoundException;

    Comment getById(long id) throws EntityNotFoundException;

    Comment update(long id, String newMessage) throws EntityNotFoundException;

    Comment add(long id, String message) throws EntityNotFoundException;
}
