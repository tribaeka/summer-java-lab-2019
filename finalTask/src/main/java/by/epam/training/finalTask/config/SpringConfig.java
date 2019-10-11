package by.epam.training.finalTask.config;

import by.epam.training.finalTask.dao.*;
import by.epam.training.finalTask.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "by.epam.training.finalTask")
public class SpringConfig {

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/library");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public UserDao getUserDao(){
        return new UserDaoImpl(getJdbcTemplate());
    }

    @Bean
    public BookDao getBookDao(){
        return new BookDaoImpl(getJdbcTemplate());
    }

    @Bean
    public ChapterDao getChapterDao(){
        return new ChapterDaoImpl(getJdbcTemplate());
    }

    @Bean
    public GenreDao getGenreDao(){
        return new GenreDaoImpl(getJdbcTemplate());
    }

    @Bean
    public UserService getUserService(){
        return new UserService(getUserDao(), getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
