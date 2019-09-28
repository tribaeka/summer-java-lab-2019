package by.epam.training.task12.controller;

import by.epam.training.task12.domain.User;
import by.epam.training.task12.repo.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private ObjectMapper mapper = new ObjectMapper();
    private final UserRepo userRepo;
    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<User> list(){
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") User user){
        return user;
    }

    @PostMapping
    public User create(@RequestBody String jsonUser){
        User user = null;
        try {
            user = mapper.readValue(jsonUser, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userRepo.save(user);
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") User userFromDb,
                          @RequestBody String jsonUser
    ){
        User user = null;
        try {
            user = mapper.readValue(jsonUser, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(user, userFromDb, "id");

        return userRepo.save(userFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user){
        userRepo.delete(user);
    }
}
