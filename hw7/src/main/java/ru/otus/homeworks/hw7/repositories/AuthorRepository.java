package ru.otus.homeworks.hw7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeworks.hw7.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
