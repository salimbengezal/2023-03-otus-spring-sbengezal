package ru.otus.homeworks.hw10.service;

import ru.otus.homeworks.hw10.dto.AuthorDtoResponse;

import java.util.List;

public interface AuthorService {

    List<AuthorDtoResponse> getAll();

}
