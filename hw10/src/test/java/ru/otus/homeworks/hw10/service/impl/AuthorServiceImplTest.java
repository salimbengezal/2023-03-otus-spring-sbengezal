package ru.otus.homeworks.hw10.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homeworks.hw10.entity.Author;
import ru.otus.homeworks.hw10.mapper.AuthorMapper;
import ru.otus.homeworks.hw10.repository.AuthorRepository;
import ru.otus.homeworks.hw10.service.AuthorService;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Сервис с авторами должен ")
public class AuthorServiceImplTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorMapper mapper;

    @Test
    @DisplayName("получать всех авторов")
    void shouldGetAllAuthors() {
        val authors = IntStream.range(1, 10)
                .mapToObj(number -> new Author(String.valueOf(number), "a" + number))
                .toList();
        when(authorRepository.findAll()).thenReturn(authors);
        val authorsDto = mapper.toDto(authors);
        assertEquals(authorService.getAll(), authorsDto);
        verify(authorRepository, times(1)).findAll();
    }

}
