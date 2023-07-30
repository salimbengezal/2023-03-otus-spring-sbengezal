package ru.otus.homeworks.hw10.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw10.dto.AuthorDtoResponse;
import ru.otus.homeworks.hw10.mapper.AuthorMapper;
import ru.otus.homeworks.hw10.repository.AuthorRepository;
import ru.otus.homeworks.hw10.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    private final AuthorMapper mapper;

    @Override
    public List<AuthorDtoResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

}
