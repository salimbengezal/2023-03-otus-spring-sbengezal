package ru.otus.homeworks.hw6.repositories.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homeworks.hw6.entity.Comment;
import ru.otus.homeworks.hw6.repositories.CommentRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentJpaRepository implements CommentRepository {

    private final EntityManager em;

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        em.remove(comment);
    }


}
