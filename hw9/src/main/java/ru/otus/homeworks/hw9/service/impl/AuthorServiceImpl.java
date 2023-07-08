package ru.otus.homeworks.hw9.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw9.entity.Author;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw9.repositories.AuthorRepository;
import ru.otus.homeworks.hw9.service.AuthorService;

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
    public Author getById(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Автор не найден"));
    }

    @Override
    public List<Author> getAllByNameContains(String text) {
        return repository.findByNameContainingIgnoreCase(text);
    }

}
