package ru.otus.homeworks.hw10.dto;

import java.util.List;

public record BookDetailsDtoResponse(BookDtoResponse book, List<CommentDtoResponse> comments) {
}
