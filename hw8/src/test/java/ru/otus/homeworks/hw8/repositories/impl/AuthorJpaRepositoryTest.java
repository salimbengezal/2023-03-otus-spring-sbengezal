package ru.otus.homeworks.hw8.repositories.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homeworks.hw8.entity.Author;
import ru.otus.homeworks.hw8.repositories.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@DisplayName("Репозиторий с авторами должен ")
public class AuthorJpaRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @BeforeAll
    static void fillData(@Autowired MongoTemplate mongoTemplate){
        val author1 = Author.builder().id("1L").name("автор1").build();
        val author2 = Author.builder().id("2L").name("автор2").build();
        mongoTemplate.save(author1);
        mongoTemplate.save(author2);
    }

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expected = new Author("1L", "автор1");
        val author = repository.findById("1L").orElseThrow();
        assertEquals(expected, author);
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DisplayName("вернуть пустой результат")
    void shouldReturnEmptyEntity() {
        assert (repository.findById("-1L").isEmpty());
    }

}
