package by.epam.training.task10;

import by.epam.training.task10.model.Database;
import by.epam.training.task10.model.User;
import by.epam.training.task10.service.AlphaService;

public class Runner {
    public static void main(String[] args) {
        Database library = new Database("jdbc:mysql://localhost:8083/library",
                "root", "root");
        /*AlphaService service = new AlphaService(library);
        String genreForTest = "Adventure";
        User user = new User("test_name", "test_password", "test_email");
        service.addUser(user);
        service.updateChapterTitle(service.getRandomChapter(), "newTitle");
        service.deleteChapter(service.getRandomChapter());
        for(Integer id : service.getBooksIdByGenre(genreForTest)){
            System.out.println(service.getBooksTitleById(id));
        }*/
    }
}
