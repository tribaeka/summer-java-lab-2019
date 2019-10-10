package by.epam.training.finalTask.dao;

import by.epam.training.finalTask.entity.User;
import by.epam.training.finalTask.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String sql = "SELECT * FROM user WHERE username = '" + username + "';";//TODO
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper());
        }catch (EmptyResultDataAccessException ex){
            return null;
        }

    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user(id_user, username, password, email, image_path, active)\n" +
                "  VALUE (NULL, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getImagePath());
                ps.setBoolean(5, user.isActive());
                return ps;
            }
        });
    }

    @Override
    public void updateUsersImagePath(User user) {
        String sql = "UPDATE user SET image_path = ? WHERE id_user = ?;";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getImagePath());
                ps.setInt(2, user.getId());
                return ps;
            }
        });
    }
}
