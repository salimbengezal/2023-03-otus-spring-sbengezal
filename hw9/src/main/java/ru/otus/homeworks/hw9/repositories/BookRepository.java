package ru.otus.homeworks.hw9.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeworks.hw9.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
