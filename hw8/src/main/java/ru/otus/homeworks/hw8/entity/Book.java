package ru.otus.homeworks.hw8.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Builder(toBuilder = true)
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
    private List<Comment> comments;

}
