package ru.otus.homeworks.hw6.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw6.entity.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName("Репозиторий с авторами должен ")
public class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc jdbc;

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expected = new Author(1, "автор1");
        val author = jdbc.getById(1).orElseThrow();
        assertEquals(expected, author);
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(2, jdbc.getAll().size());
    }

    @Test
    @DisplayName("вернуть пустой результат")
    void shouldReturnEmptyEntity() {
        assert (jdbc.getById(-1).isEmpty());
    }

}
