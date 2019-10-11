package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.entity.Chapter;

public interface BookDao {
    Book findBookByChapter(Chapter chapter);
}
