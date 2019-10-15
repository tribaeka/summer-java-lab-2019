package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReaderCardDaoImpl implements ReaderCardDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReaderCardDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void follow(int userId, int bookId) {
        String sql = "INSERT INTO reader_card(user_id, book_id) VALUE (?,?);";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, bookId);
                return ps;
            }
        });
    }

    @Override
    public List<Book> getFollowedBooks(int userId) {
        String sql = "SELECT * FROM book JOIN reader_card rc ON book.id_book = rc.book_id AND user_id = " + userId + ";";
        return jdbcTemplate.query(sql, new BookMapper());
    }
}
