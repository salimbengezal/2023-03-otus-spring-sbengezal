package ru.otus.homeworks.hw6.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.entity.Comment;
import ru.otus.homeworks.hw6.repositories.impl.CommentJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(value = {CommentJpaRepository.class})
@DisplayName("Репозиторий с комментариями должен ")
public class CommentJpaRepositoryTest {

    @Autowired
    private TestEntityManager tem;

    @Autowired
    private CommentJpaRepository repository;

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val comment = repository.getById(1).orElseThrow();
        assertEquals(1, comment.getId());
        assertEquals("some message1", comment.getMessage());
    }

    @Test
    @DisplayName("добавлять новую сущность")
    void shouldAddNewComment() {
        val book = Book.builder().id(1).build();
        val comment = Comment.builder()
                .message("new comment")
                .book(book)
                .build();
        repository.save(comment);
        val savedComment = tem.find(Comment.class, comment.getId());
        assertEquals(savedComment, comment);
    }

    @Test
    @DisplayName("обновлять сущность")
    void shouldUpdateComment() {
        val comment = tem.find(Comment.class, 1);
        assertNotNull(comment);
        val updatedComment = comment.toBuilder()
                .message("some changed message")
                .build();
        repository.save(updatedComment);
        assertEquals(updatedComment, comment);
    }

    @Test
    @DisplayName("удалять сущность")
    void shouldDeleteComment() {
        val comment = tem.find(Comment.class, 2);
        repository.delete(comment);
        val deletedComment = tem.find(Comment.class, 2);
        assertNull (deletedComment);
    }

}
