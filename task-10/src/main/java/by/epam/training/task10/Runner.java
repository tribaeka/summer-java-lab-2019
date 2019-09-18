package by.epam.training.task10;

import by.epam.training.task10.model.Database;
import by.epam.training.task10.model.User;

import java.sql.SQLException;

public class Runner {
    private static final String NATIVE_PASSWORD = "924462";
    public static void main(String[] args) throws SQLException {
        Database library = new Database("jdbc:mysql://localhost:8083/library",
                "root", NATIVE_PASSWORD);
        String genreForTest = "Adventure";
        User user = new User("test_name", "test_password", "test_email");
        library.runAddUserSql(user);
        library.runSqlUpdateChapterTitle(library.runSqlSelectRandomChapter(), "newTitle");
        library.runSqlDeleteChapter(library.runSqlSelectRandomChapter());
        for(Integer id : library.runSqlSelectAllBooksByGenre(genreForTest)){
            System.out.println(library.runSqlGetBooksTitleById(id));
        }
    }
}
