package ru.otus.homeworks.hw6.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw6.entity.Author;
import ru.otus.homeworks.hw6.repositories.impl.AuthorJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(AuthorJpaRepository.class)
@DisplayName("Репозиторий с авторами должен ")
public class AuthorJpaRepositoryTest {

    @Autowired
    private AuthorJpaRepository repository;

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expected = new Author(1, "автор1");
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
