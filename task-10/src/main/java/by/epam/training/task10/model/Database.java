package by.epam.training.task10.model;

import java.sql.*;

public class Database {
    private Connection conn;

    public Database(String url, String user_name, String password) {
        conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.conn = DriverManager.getConnection(url, user_name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.conn;
    }

    public void runAddUserSql(User user) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(Queries.ADD_NEW_USER);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getImagePath());
        ps.executeUpdate();
        ps.close();

    }

    public void runSqlUpdateChapterTitle(int chapterId, String newTitle) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(Queries.UPDATE_CHAPTER_TITLE_BY_ID);

        ps.setString(1, newTitle);
        ps.setInt(2, chapterId);
        ps.executeUpdate();
        ps.close();

    }

    public void runSqlDeleteChapter(int chapterId) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(Queries.DELETE_CHAPTER_BY_ID);

        ps.setInt(1, chapterId);
        ps.executeUpdate();
        ps.close();

    }

    public ResultSet runSqlSelectAllBooksByGenre(String genre) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Queries.SELECT_ALL_BOOKS_ID_BY_GENRE);

        ps.setString(1, genre);
        return ps.executeQuery();
    }

    public ResultSet runSqlSelectRandomChapter() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Queries.SELECT_RANDOM_CHAPTER);
        return ps.executeQuery();
    }

    public ResultSet runSqlGetBooksTitleById(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Queries.SELECT_BOOKS_TITLE_BY_ID);

        ps.setInt(1, id);
        return ps.executeQuery();
    }
}
