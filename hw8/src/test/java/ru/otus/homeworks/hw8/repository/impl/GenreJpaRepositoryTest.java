package ru.otus.homeworks.hw8.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.homeworks.hw8.entity.Genre;
import ru.otus.homeworks.hw8.repositories.GenreRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DisplayName("Репозиторий с жанрами должен ")
public class GenreJpaRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expected = new Genre(1, "жанр1");
        val author = repository.findById(1L).orElseThrow();
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
        assert (repository.findById(-1L).isEmpty());
    }

}
