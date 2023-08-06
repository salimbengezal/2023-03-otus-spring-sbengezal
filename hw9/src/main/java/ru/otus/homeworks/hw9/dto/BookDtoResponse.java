package ru.otus.homeworks.hw9.dto;

public record BookDtoResponse(String id, String name, Short releaseYear, AuthorDtoResponse author,
                              GenreDtoResponse genre) {
}
