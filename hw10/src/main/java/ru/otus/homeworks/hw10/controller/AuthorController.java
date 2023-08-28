package ru.otus.homeworks.hw10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homeworks.hw10.dto.AuthorDtoResponse;
import ru.otus.homeworks.hw10.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/api/author")
    public List<AuthorDtoResponse> get() {
        return authorService.getAll();
    }

}
