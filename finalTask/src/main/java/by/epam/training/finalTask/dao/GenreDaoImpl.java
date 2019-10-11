package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.entity.Genre;
import by.epam.training.finalTask.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GenreDaoImpl implements GenreDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findBooksGenres(Book book) {
        String sql = "SELECT id_genre, title FROM genre JOIN genre_book " +
                "WHERE id_genre = genre_id AND book_id = " + book.getId() + ";";
        return jdbcTemplate.query(sql, new GenreMapper());
    }
}
