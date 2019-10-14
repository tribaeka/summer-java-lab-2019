package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.entity.Chapter;

import java.util.List;

public interface ChapterDao {
    List<Chapter> findLatestUpdates();
    List<Chapter> findAllChaptersByBook(Book book);
    Chapter findChapterInBookByTitle(Book book, String title);
}
