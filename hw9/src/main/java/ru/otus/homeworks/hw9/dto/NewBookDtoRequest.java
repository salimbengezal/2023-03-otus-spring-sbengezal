package ru.otus.homeworks.hw9.dto;

public record NewBookDtoRequest(String name, Short releaseYear, String authorId, String genreId) {
}
