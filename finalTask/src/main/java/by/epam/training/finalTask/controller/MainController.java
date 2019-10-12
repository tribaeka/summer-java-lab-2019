package by.epam.training.finalTask.controller;

import by.epam.training.finalTask.entity.User;
import by.epam.training.finalTask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final BookService bookService;

    @Autowired
    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/")
    public String main(Model model, @AuthenticationPrincipal User user){

        if (user != null){
            model.addAttribute("user", user);
        }
        System.out.println("on main page with user = " + user);
        model.addAttribute("books", bookService.latestBooks());

        return "main";
    }
}
