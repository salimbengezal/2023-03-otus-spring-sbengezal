package ru.otus.homeworks.hw10.service;

import ru.otus.homeworks.hw10.dto.BookDetailsDtoResponse;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;

public interface BookDetailsService {

    BookDetailsDtoResponse getById(String id) throws EntityNotFoundException;

}
