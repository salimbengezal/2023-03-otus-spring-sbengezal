package ru.otus.homeworks.hw7.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw7.entity.Author;
import ru.otus.homeworks.hw7.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw7.repositories.AuthorRepository;
import ru.otus.homeworks.hw7.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Author getById(long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор c [id=%d] не найден".formatted(id)));
    }
}
