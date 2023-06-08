package ru.otus.homeworks.hw7.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw7.entity.Genre;
import ru.otus.homeworks.hw7.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw7.repositories.GenreRepository;
import ru.otus.homeworks.hw7.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    public Genre getById(long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Жанр c [id=%d] не найден".formatted(id)));
    }
}
