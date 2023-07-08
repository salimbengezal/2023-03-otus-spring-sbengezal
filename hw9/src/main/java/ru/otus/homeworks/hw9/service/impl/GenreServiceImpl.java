package ru.otus.homeworks.hw9.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw9.entity.Genre;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw9.repositories.GenreRepository;
import ru.otus.homeworks.hw9.service.GenreService;

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
    public Genre getById(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Жанр не найден"));
    }

    @Override
    public List<Genre> getAllByNameContains(String text) {
        return repository.findByNameContainingIgnoreCase(text);
    }

}
