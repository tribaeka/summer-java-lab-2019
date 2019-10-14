package by.epam.training.finalTask.service;

import by.epam.training.finalTask.dao.BookDao;
import by.epam.training.finalTask.dao.ChapterDao;
import by.epam.training.finalTask.dao.GenreDao;
import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookDao bookDao;
    private final ChapterDao chapterDao;
    private final GenreDao genreDao;

    @Autowired
    public BookService(BookDao bookDao, ChapterDao chapterDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.chapterDao = chapterDao;
        this.genreDao = genreDao;
    }

    public Book getBook(String titleInUrl){
        Book book = bookDao.findBookByTitle(Book.urlToTitle(titleInUrl));
        setChaptersAndGenres(book);
        return book;
    }

    public List<Book> latestBooks(){
        List<Chapter> latestChapters = chapterDao.findLatestUpdates();
        List<Book> books = new ArrayList<>();
        for (Chapter chapter : latestChapters){
            Book book = bookDao.findBookByChapter(chapter);
            setChaptersAndGenres(book);
            if (!containsById(books, book)) books.add(book);
        }
        return books;
    }

    public Chapter getChapter(String bookUrl, String chapterUrl){
        return chapterDao.findChapterInBookByTitle(getBook(bookUrl), Chapter.urlToTitle(chapterUrl));
    }

    private boolean containsById(List<Book> books, Book book){
        boolean isContains = false;
        for (Book bookFromList : books){
            if (bookFromList.getId().equals(book.getId())){
                isContains = true;
            }
        }
        return isContains;
    }

    private void setChaptersAndGenres(Book book){
        book.setChapters(chapterDao.findAllChaptersByBook(book));
        book.setGenres(genreDao.findBooksGenres(book));
    }
}
