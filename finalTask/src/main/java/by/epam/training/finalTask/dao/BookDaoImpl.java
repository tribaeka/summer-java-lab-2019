package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.entity.Chapter;
import by.epam.training.finalTask.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book findBookByChapter(Chapter chapter) {
        String sql = "SELECT * FROM book WHERE id_book = " + chapter.getBookId() + ";";
        try {
            return jdbcTemplate.queryForObject(sql, new BookMapper());
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public Book findBookByTitle(String title) {
        String sql = "SELECT * FROM book WHERE LOWER(title) LIKE \"" + title + "\";";
        try {
            return jdbcTemplate.queryForObject(sql, new BookMapper());
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }
}
