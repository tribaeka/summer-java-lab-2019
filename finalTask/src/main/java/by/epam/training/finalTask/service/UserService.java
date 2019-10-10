package by.epam.training.finalTask.service;

import by.epam.training.finalTask.dao.UserDao;
import by.epam.training.finalTask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:app.properties")
public class UserService implements UserDetailsService {

    @Value("${default.profile.image}")
    private String defaultImage;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("findUserByUsername");
        return userDao.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDb = userDao.findByUsername(user.getUsername());

        if (userFromDb != null){
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setImagePath(defaultImage);

        userDao.addUser(user);

        return true;
    }

    public void changeUsersPhoto(User user, String fileName){
        user.setImagePath("/img/" + fileName);
        userDao.updateUsersImagePath(user);
    }
}