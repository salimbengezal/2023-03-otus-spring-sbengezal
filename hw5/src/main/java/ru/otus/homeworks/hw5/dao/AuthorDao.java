package ru.otus.homeworks.hw5.dao;

import ru.otus.homeworks.hw5.entity.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();

    Author getById(long id);

}
