package ru.otus.homeworks.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw5.dao.GenreDao;
import ru.otus.homeworks.hw5.entity.Genre;
import ru.otus.homeworks.hw5.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw5.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getById(long id) throws EntityNotFoundException {
        try {
            return genreDao.getById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Жанр c [id=%d] не найден".formatted(id), e);
        }
    }
}
