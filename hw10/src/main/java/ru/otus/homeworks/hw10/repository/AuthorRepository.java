package ru.otus.homeworks.hw10.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeworks.hw10.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
