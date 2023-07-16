package ru.otus.homeworks.hw9.service;

import ru.otus.homeworks.hw9.dto.AuthorDtoResponse;

import java.util.List;

public interface AuthorService {

    List<AuthorDtoResponse> getAll();

}
