package ru.otus.homeworks.hw8.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw8.entity.Author;
import ru.otus.homeworks.hw8.repositories.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DisplayName("Репозиторий с авторами должен ")
public class AuthorJpaRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expected = new Author(1, "автор1");
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
