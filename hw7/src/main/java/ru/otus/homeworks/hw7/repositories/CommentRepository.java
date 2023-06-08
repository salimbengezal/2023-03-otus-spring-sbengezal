package ru.otus.homeworks.hw7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeworks.hw7.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
