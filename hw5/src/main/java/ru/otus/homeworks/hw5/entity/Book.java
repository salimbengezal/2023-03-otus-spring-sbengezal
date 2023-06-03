package ru.otus.homeworks.hw5.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Book {

    private final long id;

    private final String name;

    private final short releaseYear;

    private final Author author;

    private final Genre genre;

}
