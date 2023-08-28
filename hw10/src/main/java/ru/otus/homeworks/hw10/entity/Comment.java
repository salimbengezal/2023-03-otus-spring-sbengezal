package ru.otus.homeworks.hw10.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document(collection = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    private String message;

    @DocumentReference(lazy = true)
    private Book book;

    private LocalDateTime updateOn;

    public Comment(String message, Book book) {
        this.message = message;
        this.book = book;
        this.updateOn = LocalDateTime.now();
    }

    public Comment(String id, String message, Book book) {
        this.id = id;
        this.message = message;
        this.book = book;
        this.updateOn = LocalDateTime.now();
    }

}
