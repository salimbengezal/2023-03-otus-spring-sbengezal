package ru.otus.homeworks.hw10.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw10.dto.GenreDtoResponse;
import ru.otus.homeworks.hw10.entity.Genre;

import java.util.List;

@Component
public class GenreMapper {

    public GenreDtoResponse toDto(Genre genre) {
        return new GenreDtoResponse(genre.getId(), genre.getName());
    }

    public List<GenreDtoResponse> toDto(List<Genre> genres) {
        return genres.stream().map(this::toDto).toList();
    }
}
