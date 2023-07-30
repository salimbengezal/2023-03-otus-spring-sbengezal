package ru.otus.homeworks.hw10.service.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw10.dto.GenreDtoResponse;
import ru.otus.homeworks.hw10.entity.Genre;
import ru.otus.homeworks.hw10.repository.GenreRepository;
import ru.otus.homeworks.hw10.service.GenreService;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Сервис с жанрами должен ")
@Import(AuthorServiceImpl.class)
public class GenreServiceImplTest {

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    @BeforeEach
    void fillData() {
        val genres = IntStream.range(1, 10)
                .mapToObj(number -> new Genre(String.valueOf(number), "a" + number))
                .toList();
        when(genreRepository.findAll()).thenReturn(genres);
    }

    @Test
    @DisplayName("получать всех авторов")
    void shouldGetAllAuthors() {
        val genresDto = IntStream.range(1, 10)
                .mapToObj(number -> new GenreDtoResponse(String.valueOf(number), "a" + number))
                .toList();
        assertEquals(genreService.getAll(), genresDto);
        verify(genreRepository, times(1)).findAll();
    }

}
