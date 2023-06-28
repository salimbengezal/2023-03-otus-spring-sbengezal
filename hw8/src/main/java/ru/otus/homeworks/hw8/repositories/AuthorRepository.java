package ru.otus.homeworks.hw8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeworks.hw8.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
