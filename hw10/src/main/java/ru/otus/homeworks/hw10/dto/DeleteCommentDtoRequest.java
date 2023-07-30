package ru.otus.homeworks.hw10.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteCommentDtoRequest(
        @NotBlank
        String bookId,
        @NotBlank
        String commentId
) {
}
