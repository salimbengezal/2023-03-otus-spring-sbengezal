package ru.otus.homeworks.hw9.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw9.dto.AuthorDto;
import ru.otus.homeworks.hw9.repositories.AuthorRepository;
import ru.otus.homeworks.hw9.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public List<AuthorDto> getAll() {
        return repository.findAll().stream()
                .map(e -> new AuthorDto(e.getId(), e.getName()))
                .toList();
    }

}
