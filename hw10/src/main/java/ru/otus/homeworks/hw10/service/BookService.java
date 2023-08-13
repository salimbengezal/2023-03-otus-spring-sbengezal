package ru.otus.homeworks.hw10.service;

import ru.otus.homeworks.hw10.dto.BookDtoResponse;
import ru.otus.homeworks.hw10.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw10.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;

import java.util.List;

public interface BookService {

    List<BookDtoResponse> getAll();

    void deleteById(String id) throws EntityNotFoundException;

    void update(String id, UpdateBookDtoRequest bookDto) throws EntityNotFoundException;

    void add(NewBookDtoRequest bookDto) throws EntityNotFoundException;


}
