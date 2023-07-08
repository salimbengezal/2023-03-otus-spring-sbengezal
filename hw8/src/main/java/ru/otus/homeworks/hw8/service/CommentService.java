package ru.otus.homeworks.hw8.service;

import ru.otus.homeworks.hw8.entity.Comment;
import ru.otus.homeworks.hw8.exceptions.EntityNotFoundException;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBookId(String id);

    void deleteById(String id) throws EntityNotFoundException;

    Comment getById(String id) throws EntityNotFoundException;

    Comment update(String id, String newMessage) throws EntityNotFoundException;

    Comment add(String id, String message) throws EntityNotFoundException;
}
