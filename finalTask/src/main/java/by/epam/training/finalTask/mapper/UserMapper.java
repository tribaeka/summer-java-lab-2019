package by.epam.training.finalTask.mapper;

import by.epam.training.finalTask.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user  = new User();
        user.setId(resultSet.getInt("id_user"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setImagePath(resultSet.getString("image_path"));
        user.setActive(resultSet.getBoolean("active"));
        return user;
    }
}
