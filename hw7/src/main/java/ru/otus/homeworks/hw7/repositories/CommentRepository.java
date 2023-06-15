package ru.otus.homeworks.hw7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeworks.hw7.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBookId(long bookId);

}
