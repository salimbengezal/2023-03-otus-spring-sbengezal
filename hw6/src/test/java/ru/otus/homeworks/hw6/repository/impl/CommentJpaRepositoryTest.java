package ru.otus.homeworks.hw6.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.entity.Comment;
import ru.otus.homeworks.hw6.repositories.impl.CommentJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(value = {CommentJpaRepository.class})
@DisplayName("Репозиторий с комментариями должен ")
public class CommentJpaRepositoryTest {

    @Autowired
    private CommentJpaRepository repository;

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expectedBook = Book.builder()
                .id(1)
                .build();
        val expectedComment = Comment.builder().id(1).message("some message1").book(expectedBook).build();
        val comment = repository.getById(1).orElseThrow();
        assertEquals(expectedComment.getId(), comment.getId());
        assertEquals(expectedComment.getMessage(), comment.getMessage());
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(2, repository.getAllByBookId(1).size());
    }

    @Test
    @DisplayName("добавлять новую сущность")
    void shouldAddNewComment() {
        val book = Book.builder().id(1).build();
        val newComment = Comment.builder()
                .message("new comment")
                .book(book)
                .build();
        val count = repository.getAllByBookId(book.getId()).size();
        val addedComment = repository.save(newComment);
        assertEquals(count + 1, repository.getAllByBookId(book.getId()).size());
        assertEquals(addedComment, newComment);
    }

    @Test
    @DisplayName("обновлять сущность")
    void shouldUpdateComment() {
        val comment = repository.getById(1).orElseThrow();
        val updatedComment = comment.toBuilder()
                .message("some changed message")
                .build();
        val count = repository.getAllByBookId(1).size();
        val savedComment = repository.save(updatedComment);
        assertEquals(count, repository.getAllByBookId(1).size());
        assertEquals(savedComment, updatedComment);
    }

    @Test
    @DisplayName("удалять сущность")
    void shouldDeleteComment() {
        val comments = repository.getAllByBookId(1);
        val removingComment = comments.get(0);
        val count = comments.size();
        repository.delete(removingComment);
        assertEquals(count - 1, repository.getAllByBookId(1).size());
    }

}
