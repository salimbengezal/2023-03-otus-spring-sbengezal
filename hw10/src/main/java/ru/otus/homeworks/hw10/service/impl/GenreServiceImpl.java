package ru.otus.homeworks.hw10.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw10.dto.GenreDtoResponse;
import ru.otus.homeworks.hw10.mapper.GenreMapper;
import ru.otus.homeworks.hw10.repository.GenreRepository;
import ru.otus.homeworks.hw10.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    private final GenreMapper mapper;

    @Override
    public List<GenreDtoResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

}
