package by.epam.training.finalTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/greeting")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/")
    public String main(Model model){
        return "main";
    }
}
