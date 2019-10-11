package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> findBooksGenres(Book book);
}
