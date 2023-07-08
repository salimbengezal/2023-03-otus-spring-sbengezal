package ru.otus.homeworks.hw9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;

    private String name;

    private short releaseYear;

    @DocumentReference(lazy = true)
    private Author author;

    @DocumentReference(lazy = true)
    private Genre genre;

    @DBRef(lazy = true)
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

    public Book(String name, short releaseYear, Author author, Genre genre) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.author = author;
        this.genre = genre;
    }

    public Book(String id, String name, short releaseYear, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.author = author;
        this.genre = genre;
    }
}
