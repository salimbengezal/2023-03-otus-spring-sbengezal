package ru.otus.homeworks.hw9.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBookDtoRequest {

    @NotBlank(message = "Name field should not be blank")
    @Size(min = 1, max = 64, message = "Name should be expected size")
    private String name;

    @NotNull(message = "Field year should be passed")
    @Min(value = 0, message = "Field year must be more or equal 0")
    private Short releaseYear;

    @NotBlank(message = "Author field should not be blank")
    private String authorId;

    @NotBlank(message = "Genre field should not be blank")
    private String genreId;

}
