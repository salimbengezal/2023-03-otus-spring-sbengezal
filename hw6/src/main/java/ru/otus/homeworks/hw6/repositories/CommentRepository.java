package ru.otus.homeworks.hw6.repositories;

import ru.otus.homeworks.hw6.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> getAllByBookId(long id);

    Optional<Comment> getById(long id);

    Comment save(Comment comment);

    void delete(Comment comment);

}
