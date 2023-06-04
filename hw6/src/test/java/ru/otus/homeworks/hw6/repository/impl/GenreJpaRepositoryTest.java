package ru.otus.homeworks.hw6.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw6.entity.Genre;
import ru.otus.homeworks.hw6.repositories.impl.GenreJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(GenreJpaRepository.class)
@DisplayName("Репозиторий с жанрами должен ")
public class GenreJpaRepositoryTest {

    @Autowired
    private GenreJpaRepository repository;

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expected = new Genre(1, "жанр1");
        val author = repository.getById(1).orElseThrow();
        assertEquals(expected, author);
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(2, repository.getAll().size());
    }

    @Test
    @DisplayName("вернуть пустой результат")
    void shouldReturnEmptyEntity() {
        assert (repository.getById(-1).isEmpty());
    }

}
