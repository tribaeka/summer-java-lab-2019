package by.epam.training.finalTask.controller;

import by.epam.training.finalTask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    private final BookService bookService;

    @Autowired
    public ChapterController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{bookUrl}/{chapterUrl}")
    public String getChapter(Model model, @PathVariable("bookUrl") String bookUrl,
                             @PathVariable("chapterUrl") String chapterUrl){
        model.addAttribute("chapter", bookService.getChapter(bookUrl, chapterUrl));

        return "chapter";
    }
}
