package ru.otus.homeworks.hw9.dto;

public record BookDtoResponse(String id, String name, Short releaseYear, AuthorDto author, GenreDto genre) {
}
