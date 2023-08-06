package ru.otus.homeworks.hw9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    private String id;

    private String name;

}
