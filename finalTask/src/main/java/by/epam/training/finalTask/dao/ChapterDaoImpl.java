package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.entity.Chapter;
import by.epam.training.finalTask.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ChapterDaoImpl implements ChapterDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChapterDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Chapter> findLatestUpdates() {
        String sql = "SELECT * FROM chapter ORDER BY upload_date DESC;";
        return jdbcTemplate.query(sql, new ChapterMapper());
    }

    @Override
    public List<Chapter> findAllChaptersByBook(Book book) {
        String sql = "SELECT * FROM chapter WHERE book_id = " + book.getId() + ";";
        return jdbcTemplate.query(sql, new ChapterMapper());
    }

    @Override
    public Chapter findChapterInBookByTitle(Book book, String title) {
        title = title + "%";
        String sql = "SELECT * FROM chapter WHERE  book_id = " + book.getId() +
                " AND title LIKE '"+ title +"';";
        return jdbcTemplate.queryForObject(sql, new ChapterMapper());
    }
}
