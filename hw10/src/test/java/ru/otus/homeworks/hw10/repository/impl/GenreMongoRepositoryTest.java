package ru.otus.homeworks.hw10.repository.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homeworks.hw10.entity.Genre;
import ru.otus.homeworks.hw10.repository.GenreRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@DisplayName("Репозиторий с жанрами должен ")
public class GenreMongoRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @BeforeAll
    static void fillData(@Autowired MongoTemplate mongoTemplate) {
        val genre1 = new Genre("1L", "жанр1");
        val genre2 = new Genre("2L", "жанр2");
        mongoTemplate.save(genre1);
        mongoTemplate.save(genre2);
    }

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expected = new Genre("1L", "жанр1");
        val author = genreRepository.findById("1L").orElseThrow();
        assertEquals(expected, author);
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(2, genreRepository.findAll().size());
    }

    @Test
    @DisplayName("вернуть пустой результат")
    void shouldReturnEmptyEntity() {
        assert (genreRepository.findById("-1L").isEmpty());
    }

}
