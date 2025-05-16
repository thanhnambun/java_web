package service;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import respository.UserDAO;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(String username, String password) {
        return userDAO.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public void register(User user) {
        userDAO.addUser(user);
    }
}
