package ru.otus.homeworks.hw6.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.homeworks.hw6.dao.AuthorDao;
import ru.otus.homeworks.hw6.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcTemplate jdbc;

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Author(id, name);
        }
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("SELECT id, name FROM author", new AuthorMapper());
    }

    @Override
    public Optional<Author> getById(long id) {
        return jdbc.query("SELECT id, name FROM author WHERE id = :id",
                Map.of("id", id),
                new AuthorMapper()).stream().findFirst();
    }
}
