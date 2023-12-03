package repository.user;

import model.User;
import model.validator.Notification;

import java.util.*;

public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    void removeAll();

    boolean existsByUsername(String username);
    Boolean deleteUser(Long id);
    void updateUsername(Long userId, String username);
    void updatePassword(Long userId, String password);
    List <User> findAllEmployee();
}