package service.user;

import model.User;
import model.validator.Notification;
import repository.user.UserRepository;

import java.util.List;
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);

    }

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeAll() {
        userRepository.removeAll();

    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public void updateUsername(Long userId, String username) {
        userRepository.updateUsername(userId,username);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        userRepository.updatePassword(userId,password);
    }


}
