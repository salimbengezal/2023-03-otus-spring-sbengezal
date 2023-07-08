package ru.otus.homeworks.hw9.repositories.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homeworks.hw9.entity.Author;
import ru.otus.homeworks.hw9.entity.Book;
import ru.otus.homeworks.hw9.entity.Comment;
import ru.otus.homeworks.hw9.entity.Genre;
import ru.otus.homeworks.hw9.repositories.CommentRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Репозиторий с комментариями должен ")
public class CommentMongoRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository repository;

    @BeforeEach
    void fillData(@Autowired MongoTemplate mongoTemplate) {
        val author1 = new Author("1L", "автор1");
        val genre1 = new Genre("1L", "жанр1");
        val book1 = new Book("1L", "книга1", (short) 200, author1, genre1);
        val comment1 = new Comment("1L", "some message1", book1);
        val comment2 = new Comment("2L", "some message2", book1);
        mongoTemplate.save(author1);
        mongoTemplate.save(genre1);
        mongoTemplate.save(book1);
        mongoTemplate.save(comment1);
        mongoTemplate.save(comment2);
    }

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val comment = repository.findById("1L").orElseThrow();
        assertEquals("1L", comment.getId());
        assertEquals("some message1", comment.getMessage());
    }

    @Test
    @DisplayName("добавлять новую сущность")
    void shouldAddNewComment() {
        val book = mongoTemplate.findById("1L", Book.class);
        val comment = new Comment("some_id", "new comment", book, LocalDateTime.now());
        repository.save(comment);
        val savedComment = mongoTemplate.findById("some_id", Comment.class);
        assertNotNull(savedComment);
        assertEquals(comment.getId(), savedComment.getId());
        assertEquals(comment.getBook(), savedComment.getBook());
        assertEquals(comment.getMessage(), savedComment.getMessage());
    }

    @Test
    @DisplayName("обновлять сущность")
    void shouldUpdateComment() {
        val comment = mongoTemplate.findById("1L", Comment.class);
        assertNotNull(comment);
        comment.setMessage("some changed message");
        repository.save(comment);
        val saved = mongoTemplate.findById("1L", Comment.class);
        assertEquals(comment, saved);
    }

    @Test
    @DisplayName("удалять сущность")
    void shouldDeleteComment() {
        val comment = mongoTemplate.findById("2L", Comment.class);
        assertNotNull(comment);
        repository.delete(comment);
        val deletedComment = mongoTemplate.findById("2L", Comment.class);
        assertNull(deletedComment);
    }

}
