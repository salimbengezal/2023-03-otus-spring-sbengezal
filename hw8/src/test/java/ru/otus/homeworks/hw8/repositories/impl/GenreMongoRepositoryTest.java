package ru.otus.homeworks.hw8.repositories.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homeworks.hw8.entity.Genre;
import ru.otus.homeworks.hw8.repositories.GenreRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@DisplayName("Репозиторий с жанрами должен ")
public class GenreMongoRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @BeforeAll
    static void fillData(@Autowired MongoTemplate mongoTemplate){
        val genre1 = Genre.builder().id("1L").name("жанр1").build();
        val genre2 = Genre.builder().id("2L").name("жанр2").build();
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
