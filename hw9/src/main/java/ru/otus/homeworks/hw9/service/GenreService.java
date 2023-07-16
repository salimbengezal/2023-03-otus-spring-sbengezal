package ru.otus.homeworks.hw9.service;

import ru.otus.homeworks.hw9.dto.GenreDtoResponse;

import java.util.List;

public interface GenreService {

    List<GenreDtoResponse> getAll();

}
