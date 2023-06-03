package ru.otus.homeworks.hw6.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.homeworks.hw6.dao.GenreDao;
import ru.otus.homeworks.hw6.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcTemplate jdbc;

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT id, name FROM genre", new GenreMapper());
    }

    @Override
    public Optional<Genre> getById(long id) {
        return jdbc.query("SELECT id, name FROM genre WHERE id = :id",
                Map.of("id", id),
                new GenreMapper()).stream().findFirst();
    }
}
