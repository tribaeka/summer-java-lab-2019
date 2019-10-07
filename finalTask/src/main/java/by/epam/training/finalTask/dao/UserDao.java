package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
