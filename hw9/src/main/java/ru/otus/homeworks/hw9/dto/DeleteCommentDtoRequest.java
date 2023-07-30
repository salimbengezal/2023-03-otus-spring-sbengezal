package ru.otus.homeworks.hw9.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteCommentDtoRequest(
        @NotBlank
        String bookId,
        @NotBlank
        String commentId
) {
}
