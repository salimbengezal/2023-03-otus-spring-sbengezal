package ru.otus.homeworks.hw8.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeworks.hw8.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph("book-author-genre-entity-graph")
    List<Book> findAll();

    @Override
    @EntityGraph("book-author-genre-entity-graph")
    Optional<Book> findById(Long aLong);

}
