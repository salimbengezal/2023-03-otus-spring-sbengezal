package ru.otus.homeworks.hw9.dto;

public record UpdateBookDtoRequest(String id, String name, Short releaseYear, String authorId, String genreId) {
}
