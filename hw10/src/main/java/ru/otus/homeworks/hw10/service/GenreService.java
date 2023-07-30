package ru.otus.homeworks.hw10.service;

import ru.otus.homeworks.hw10.dto.GenreDtoResponse;

import java.util.List;

public interface GenreService {

    List<GenreDtoResponse> getAll();

}
