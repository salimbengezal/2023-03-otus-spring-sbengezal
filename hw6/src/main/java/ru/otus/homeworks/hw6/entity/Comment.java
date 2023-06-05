package ru.otus.homeworks.hw6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity(name = "comment")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@NamedEntityGraph(name = "comment-book-entity-graph", attributeNodes = {@NamedAttributeNode("book")})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @UpdateTimestamp
    private Timestamp updateOn;

}
