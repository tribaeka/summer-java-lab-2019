package by.epam.training.task10.model;

public class Queries {
    public static final String ADD_NEW_USER = "INSERT INTO user(id_user, username, password, email, image_path, active)" +
            "  VALUE (NULL, ?, ?, ?, ?, 0);";
    public static final String UPDATE_CHAPTER_TITLE_BY_ID = "UPDATE chapter SET title = ? WHERE id_chapter = ?;";
    public static final String DELETE_CHAPTER_BY_ID = "DELETE FROM chapter WHERE id_chapter = ?;";
    public static final String SELECT_ALL_BOOKS_ID_BY_GENRE = "SELECT b.id_book\n" +
            "FROM book b\n" +
            "INNER JOIN genre_book gb on b.id_book = gb.book_id\n" +
            "INNER JOIN genre g on gb.genre_id = g.id_genre\n" +
            "WHERE g.title = ?;";
    public static final String SELECT_BOOKS_TITLE_BY_ID = "SELECT title FROm book WHERE id_book = ?;";
    public static final String SELECT_RANDOM_CHAPTER = "SELECT * FROM chapter ORDER BY RAND() LIMIT 1;";
}
