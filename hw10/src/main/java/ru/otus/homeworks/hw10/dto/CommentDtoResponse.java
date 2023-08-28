package ru.otus.homeworks.hw10.dto;

import java.time.LocalDateTime;

public record CommentDtoResponse(String id, String message, LocalDateTime updatedOn) {
}
