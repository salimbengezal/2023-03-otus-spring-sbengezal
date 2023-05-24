package ru.otus.homeworks.hw5.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homeworks.hw5.dao.BookDao;
import ru.otus.homeworks.hw5.entity.Author;
import ru.otus.homeworks.hw5.entity.Book;
import ru.otus.homeworks.hw5.entity.Genre;
import ru.otus.homeworks.hw5.exceptions.KeyExtractorException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "SELECT b.id, b.name as name, release_year, g.id as genre_id, g.name as genre_name, a.id as author_id, a.name as author_name " +
                        "FROM book b " +
                        "JOIN author a ON b.author_id = a.id " +
                        "JOIN genre g ON b.genre_id = g.id",
                new BookMapper());
    }

    @Override
    public Book getById(long id) {
        return jdbc.queryForObject(
                "SELECT b.id, b.name as name, release_year, g.id as genre_id, g.name as genre_name, a.id as author_id, a.name as author_name " +
                        "FROM book b " +
                        "JOIN author a ON b.author_id = a.id " +
                        "JOIN genre g ON b.genre_id = g.id " +
                        "WHERE b.id  =:id",
                Map.of("id", id),
                new BookMapper()
        );
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("DELETE FROM book WHERE id = :id", Map.of("id", id));
    }

    @Override
    public Book add(Book book) {
        val parameters = Map.of(
                "name", book.getName(),
                "year", book.getReleaseYear(),
                "genre_id", book.getGenre().getId(),
                "author_id", book.getAuthor().getId());
        val paramSource = new MapSqlParameterSource(parameters);
        val keyHolder = new GeneratedKeyHolder();
        jdbc.update("INSERT INTO book (name, release_year, genre_id, author_id) " +
                        "VALUES(:name, :year, :genre_id, :author_id)",
                paramSource,
                keyHolder,
                new String[]{"id"}
        );
        val id = Optional.ofNullable(keyHolder.getKey())
                .orElseThrow(KeyExtractorException::new)
                .longValue();

        return book.toBuilder().id(id).build();

    }

    @Override
    public void update(Book book) {
        jdbc.update("UPDATE book " +
                        "SET name = :name, release_year = :year, genre_id = :genre_id, author_id = :author_id " +
                        "WHERE id = :id",
                Map.of(
                        "id", book.getId(),
                        "name", book.getName(),
                        "year", book.getReleaseYear(),
                        "genre_id", book.getGenre().getId(),
                        "author_id", book.getAuthor().getId()
                ));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            val id = rs.getLong("id");
            val name = rs.getString("name");
            val year = rs.getShort("release_year");
            val authorId = rs.getLong("author_id");
            val authorName = rs.getString("author_name");
            val genreId = rs.getLong("genre_id");
            val genreName = rs.getString("genre_name");
            return Book.builder()
                    .id(id)
                    .name(name)
                    .releaseYear(year)
                    .author(new Author(authorId, authorName))
                    .genre(new Genre(genreId, genreName))
                    .build();
        }
    }
}
