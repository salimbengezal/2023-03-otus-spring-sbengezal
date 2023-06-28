package ru.otus.homeworks.hw8.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeworks.hw8.entity.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByNameContainingIgnoreCase(String name);

}
