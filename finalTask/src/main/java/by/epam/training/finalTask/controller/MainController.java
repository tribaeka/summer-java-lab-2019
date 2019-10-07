package by.epam.training.finalTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/greeting")
    public String greeting(){
        return "greeting";
    }
}
