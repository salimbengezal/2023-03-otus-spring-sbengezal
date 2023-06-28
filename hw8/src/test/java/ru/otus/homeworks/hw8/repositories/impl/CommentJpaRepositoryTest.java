package ru.otus.homeworks.hw8.repositories.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.homeworks.hw8.entity.Author;
import ru.otus.homeworks.hw8.entity.Book;
import ru.otus.homeworks.hw8.entity.Comment;
import ru.otus.homeworks.hw8.entity.Genre;
import ru.otus.homeworks.hw8.repositories.CommentRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Репозиторий с комментариями должен ")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CommentJpaRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository repository;

    @BeforeEach
    void fillData(@Autowired MongoTemplate mongoTemplate) {
        val author1 = Author.builder().id("1L").name("автор1").build();
        val genre1 = Genre.builder().id("1L").name("жанр1").build();
        val book1 = Book.builder().id("1L").name("книга1").releaseYear((short) 2000).author(author1).genre(genre1).build();
        val comment1 = Comment.builder().id("1L").book(book1).message("some message1").build();
        val comment2 = Comment.builder().id("2L").book(book1).message("some message2").build();
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
        val comment = Comment.builder()
                .id("some_id")
                .message("new comment")
                .book(book)
                .build();
        repository.save(comment);
        val savedComment = mongoTemplate.findById("some_id", Comment.class);
        assertEquals(savedComment, comment);
    }

    @Test
    @DisplayName("обновлять сущность")
    void shouldUpdateComment() {
        val comment = mongoTemplate.findById("1L", Comment.class);
        assertNotNull(comment);
        val updatedComment = comment.toBuilder()
                .message("some changed message")
                .build();
        repository.save(updatedComment);
        val saved = mongoTemplate.findById("1L", Comment.class);
        assertEquals(updatedComment, saved);
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
