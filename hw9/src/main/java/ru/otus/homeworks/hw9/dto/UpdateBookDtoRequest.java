package ru.otus.homeworks.hw9.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateBookDtoRequest(
        @NotBlank(message = "Id field should not be blank")
        String id,
        @NotBlank(message = "Name field should not be blank")
        @Size(min = 1, max = 64, message = "Name should be expected size")
        String name,
        @NotNull(message = "Field year should be passed")
        @Min(value = 0, message = "Field year must be more or equal 0")
        Short releaseYear,
        @NotBlank(message = "AuthorId field should not be blank")
        String authorId,
        @NotBlank(message = "GenreId field should not be blank")
        String genreId
) {
}
