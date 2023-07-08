package ru.otus.homeworks.hw8.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeworks.hw8.entity.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {

    List<Genre> findByNameContainingIgnoreCase(String name);

}
