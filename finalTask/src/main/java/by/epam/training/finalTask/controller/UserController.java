package by.epam.training.finalTask.controller;

import by.epam.training.finalTask.entity.Book;
import by.epam.training.finalTask.entity.User;
import by.epam.training.finalTask.service.BookService;
import by.epam.training.finalTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        List<Book> filledBookList = userService.loadFollowedBooks(user).getFollowedBooks()
                .stream()
                .map(bookService::loadChaptersAndGenres)
                .collect(Collectors.toList());
        user.setFollowedBooks(filledBookList);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("all")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "usersList";
    }

    @PostMapping("changePhoto")
    public String changePhoto(@AuthenticationPrincipal User user,
                              @RequestParam("file") MultipartFile file) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()){

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String fileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
            String filePath = uploadPath + "/" + fileName;

            file.transferTo(new File(filePath));
            userService.changeUsersPhoto(user, fileName);
        }

        return "redirect:/user";
    }

    @PostMapping("follow")
    public String follow(@AuthenticationPrincipal User user, @RequestParam("bookId") int bookId){
        userService.follow(user, bookId);
        return "redirect:/user/profile";
    }
    @PostMapping("unfollow")
    public String unFollow(@AuthenticationPrincipal User user, @RequestParam("bookId") int bookId){
        userService.unFollow(user, bookId);
        return "redirect:/user/profile";
    }
}
