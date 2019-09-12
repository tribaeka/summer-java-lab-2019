package by.epam.training.task10.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void runSqlUpdateChapterTitle(Chapter chapter, String newTitle) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(Queries.UPDATE_CHAPTER_TITLE_BY_ID);

        ps.setString(1, newTitle);
        ps.setInt(2, chapter.getId());
        ps.executeUpdate();
        ps.close();
    }

    public void runSqlDeleteChapter(Chapter chapter) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(Queries.DELETE_CHAPTER_BY_ID);

        ps.setInt(1, chapter.getId());
        ps.executeUpdate();
        ps.close();
    }

    public List<Integer> runSqlSelectAllBooksByGenre(String genre) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Queries.SELECT_ALL_BOOKS_ID_BY_GENRE);
        ps.setString(1, genre);

        List<Integer> answer = new ArrayList<>();
        try(ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                answer.add(rs.getInt("id_book"));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ps.close();

        return answer;
    }

    public Chapter runSqlSelectRandomChapter() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Queries.SELECT_RANDOM_CHAPTER);

        Chapter chapter = new Chapter();
        try(ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                chapter.setId(rs.getInt("id_chapter"));
                chapter.setTitle(rs.getString("title"));
                chapter.setContent(rs.getString("content"));
                chapter.setUpload_date(rs.getString("upload_date"));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ps.close();

        return chapter;
    }

    public String runSqlGetBooksTitleById(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(Queries.SELECT_BOOKS_TITLE_BY_ID);
        ps.setInt(1, id);

        String title = null;
        try(ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                title = rs.getString("title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ps.close();

        return title;
    }
}
