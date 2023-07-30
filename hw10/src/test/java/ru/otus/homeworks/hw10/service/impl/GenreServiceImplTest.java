package ru.otus.homeworks.hw10.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw10.entity.Genre;
import ru.otus.homeworks.hw10.mapper.GenreMapper;
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

    @Autowired
    private GenreMapper mapper;

    @Test
    @DisplayName("получать всех авторов")
    void shouldGetAllAuthors() {
        val genres = IntStream.range(1, 10)
                .mapToObj(number -> new Genre(String.valueOf(number), "a" + number))
                .toList();
        when(genreRepository.findAll()).thenReturn(genres);
        val genresDto = mapper.toDto(genres);
        assertEquals(genreService.getAll(), genresDto);
        verify(genreRepository, times(1)).findAll();
    }

}
