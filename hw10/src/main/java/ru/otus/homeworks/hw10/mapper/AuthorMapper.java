package ru.otus.homeworks.hw10.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw10.dto.AuthorDtoResponse;
import ru.otus.homeworks.hw10.entity.Author;

import java.util.List;

@Component
public class AuthorMapper {

    public AuthorDtoResponse toDto(Author author) {
        return new AuthorDtoResponse(author.getId(), author.getName());
    }

    public List<AuthorDtoResponse> toDto(List<Author> authors) {
        return authors.stream().map(this::toDto).toList();
    }

}
