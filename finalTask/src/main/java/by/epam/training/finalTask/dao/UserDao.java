package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findByUsername(String username);
    void addUser(User user);
    void updateUsersImagePath(User user);
}
