package ru.otus.homeworks.hw10.dto;

public record BookDtoResponse(String id, String name, Short releaseYear, AuthorDtoResponse author,
                              GenreDtoResponse genre) {
}
