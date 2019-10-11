package by.epam.training.finalTask.mapper;

import by.epam.training.finalTask.entity.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id_book"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setImagePath(resultSet.getString("image_path"));
        book.setRating(resultSet.getDouble("rating"));
        book.setAuthorId(resultSet.getInt("author_id"));
        return book;
    }
}
