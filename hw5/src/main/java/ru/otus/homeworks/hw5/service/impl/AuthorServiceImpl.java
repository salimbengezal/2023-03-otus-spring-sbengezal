package ru.otus.homeworks.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw5.dao.AuthorDao;
import ru.otus.homeworks.hw5.entity.Author;
import ru.otus.homeworks.hw5.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw5.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author getById(long id) throws EntityNotFoundException {
        return authorDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор c [id=%d] не найден".formatted(id)));
    }
}
