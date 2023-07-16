package ru.otus.homeworks.hw9.service.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw9.dto.AuthorDtoResponse;
import ru.otus.homeworks.hw9.entity.Author;
import ru.otus.homeworks.hw9.repositories.AuthorRepository;
import ru.otus.homeworks.hw9.service.AuthorService;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Сервис с авторами должен ")
@Import(AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @BeforeEach
    void fillData() {
        val authors = IntStream.range(1, 10)
                .mapToObj(number -> new Author(String.valueOf(number), "a" + number))
                .toList();
        when(authorRepository.findAll()).thenReturn(authors);
    }

    @Test
    @DisplayName("получать всех авторов")
    void shouldGetAllAuthors() {
        val authorsDto = IntStream.range(1, 10)
                .mapToObj(number -> new AuthorDtoResponse(String.valueOf(number), "a" + number))
                .toList();
        assertEquals(authorService.getAll(), authorsDto);
        verify(authorRepository, times(1)).findAll();
    }

}
