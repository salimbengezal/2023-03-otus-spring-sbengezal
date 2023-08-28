package ru.otus.homeworks.hw10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homeworks.hw10.dto.GenreDtoResponse;
import ru.otus.homeworks.hw10.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/api/genre")
    public List<GenreDtoResponse> get() {
        return genreService.getAll();
    }


}
