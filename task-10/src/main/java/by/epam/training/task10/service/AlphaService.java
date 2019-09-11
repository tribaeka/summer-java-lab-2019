package by.epam.training.task10.service;

import by.epam.training.task10.model.Chapter;
import by.epam.training.task10.model.Database;
import by.epam.training.task10.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlphaService {
    private Database db;

    public AlphaService(Database db) {
        this.db = db;
    }

    public List<Integer> getBooksIdByGenre(String genre){
        List<Integer> answer = new ArrayList<>();
        try(ResultSet rs = db.runSqlSelectAllBooksByGenre(genre)){
            while (rs.next()){
                answer.add(rs.getInt("id_book"));
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }
    public String getBooksTitleById(int id){
        String title = null;
        try(ResultSet rs = db.runSqlGetBooksTitleById(id)){
            while (rs.next()){
                title = rs.getString("title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }
    public void addUser(User user){
        try {
            db.runAddUserSql(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateChapterTitle(Chapter chapter, String title){
        try {
            db.runSqlUpdateChapterTitle(chapter.getId(), title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChapter(Chapter chapter){
        try {
            db.runSqlDeleteChapter(chapter.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Chapter getRandomChapter(){
        Chapter chapter = new Chapter();
        try(ResultSet rs = db.runSqlSelectRandomChapter()){
            while (rs.next()){
                chapter.setId(rs.getInt("id_chapter"));
                chapter.setTitle(rs.getString("title"));
                chapter.setContent(rs.getString("content"));
                chapter.setUpload_date(rs.getString("upload_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapter;
    }

}
