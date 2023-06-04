package ru.otus.homeworks.hw6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw6.repositories.GenreRepository;
import ru.otus.homeworks.hw6.entity.Genre;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw6.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public List<Genre> getAll() {
        return repository.getAll();
    }

    @Override
    public Genre getById(long id) throws EntityNotFoundException {
        return repository.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Жанр c [id=%d] не найден".formatted(id)));
    }
}
