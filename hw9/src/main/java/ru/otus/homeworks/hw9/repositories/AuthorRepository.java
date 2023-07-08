package ru.otus.homeworks.hw9.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeworks.hw9.entity.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findByNameContainingIgnoreCase(String name);

}
