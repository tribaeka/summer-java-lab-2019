package by.epam.training.finalTask.mapper;

import by.epam.training.finalTask.entity.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt("id_genre"));
        genre.setTitle(resultSet.getString("title"));
        return genre;
    }
}
