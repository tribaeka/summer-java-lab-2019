package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.Book;

import java.util.List;

public interface ReaderCardDao {
    void follow(int userId, int bookId);
    List<Book> getFollowedBooks(int userId);
}
