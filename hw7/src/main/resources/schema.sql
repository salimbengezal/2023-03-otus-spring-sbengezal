CREATE TABLE author
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(64)
);

CREATE TABLE genre
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(64)
);

CREATE TABLE book
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(256),
    release_year SMALLINT,
    author_id    BIGINT,
    genre_id     BIGINT,
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);

CREATE TABLE comment
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    book_id   BIGINT,
    message   varchar(1024),
    update_on timestamp NOT NULL DEFAULT current_timestamp,
    FOREIGN KEY (book_id) REFERENCES book (id)
);