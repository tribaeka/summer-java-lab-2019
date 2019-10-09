package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.User;
import by.epam.training.finalTask.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = '" + username + "';";
        return jdbcTemplate.queryForObject(sql, new UserMapper());
    }
}
