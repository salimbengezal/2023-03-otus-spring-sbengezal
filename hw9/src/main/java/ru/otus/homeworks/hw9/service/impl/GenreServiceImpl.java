package ru.otus.homeworks.hw9.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw9.dto.GenreDtoResponse;
import ru.otus.homeworks.hw9.repositories.GenreRepository;
import ru.otus.homeworks.hw9.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public List<GenreDtoResponse> getAll() {
        return repository.findAll().stream()
                .map(e -> new GenreDtoResponse(e.getId(), e.getName()))
                .toList();
    }

}
