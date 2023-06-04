package ru.otus.homeworks.hw6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw6.repositories.AuthorRepository;
import ru.otus.homeworks.hw6.entity.Author;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw6.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public List<Author> getAll() {
        return repository.getAll();
    }

    @Override
    public Author getById(long id) throws EntityNotFoundException {
        return repository.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор c [id=%d] не найден".formatted(id)));
    }
}
