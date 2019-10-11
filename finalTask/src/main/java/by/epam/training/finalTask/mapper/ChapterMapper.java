package by.epam.training.finalTask.mapper;

import by.epam.training.finalTask.entity.Chapter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChapterMapper implements RowMapper<Chapter> {
    @Override
    public Chapter mapRow(ResultSet resultSet, int i) throws SQLException {
        Chapter chapter = new Chapter();
        chapter.setId(resultSet.getInt("id_chapter"));
        chapter.setTitle(resultSet.getString("title"));
        chapter.setContent(resultSet.getString("content"));
        chapter.setBookId(resultSet.getInt("book_id"));
        chapter.setUploadDate(resultSet.getTimestamp("upload_date").toLocalDateTime());
        return chapter;
    }
}
