package by.epam.training.task12.repo;

import by.epam.training.task12.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
