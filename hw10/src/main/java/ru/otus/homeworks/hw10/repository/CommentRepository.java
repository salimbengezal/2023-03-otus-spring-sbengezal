package ru.otus.homeworks.hw10.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeworks.hw10.entity.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String bookId);

    void deleteAllByBookId(String bookId);

}
