package ru.otus.homeworks.hw6.repositories;

import ru.otus.homeworks.hw6.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> getById(long id);

    List<Comment> getAllByBookId(long id);

    Comment save(Comment comment);

    void delete(Comment comment);

}
