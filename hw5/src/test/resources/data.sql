INSERT INTO genre (name)
VALUES ('жанр1'),
       ('жанр2');

INSERT INTO author (name)
VALUES ('автор1'),
       ('автор2');

INSERT INTO book (name, release_year, author_id, genre_id)
VALUES ('книга1', 2000, 1, 1);