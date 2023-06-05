package ru.otus.homeworks.hw6.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@NamedEntityGraph(name = "book-author-genre-entity-graph",
        attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private short releaseYear;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Genre genre;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

}
