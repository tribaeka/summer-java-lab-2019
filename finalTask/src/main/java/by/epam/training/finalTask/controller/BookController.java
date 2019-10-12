package by.epam.training.finalTask.controller;

import by.epam.training.finalTask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{titleInUrl}")
    public String getBook(Model model, @PathVariable("titleInUrl") String titleInUrl){

        model.addAttribute("book", bookService.getBook(titleInUrl));
        return "book";
    }
}
