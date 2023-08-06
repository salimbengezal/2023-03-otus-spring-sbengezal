package ru.otus.homeworks.hw9.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CommentDtoRequest {
    @NotBlank(message = "Message should be not empty")
    @Size(min = 1, max = 1024, message = "Message should be expected size")
    private String message;

    private String bookId;
}
