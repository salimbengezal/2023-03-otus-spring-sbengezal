package ru.otus.homeworks.hw10.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeworks.hw10.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
