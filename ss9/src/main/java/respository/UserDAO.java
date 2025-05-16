package respository;

import model.User;

public interface UserDAO {
    User findUserByUsernameAndPassword(String username, String password);
    void addUser(User user);
}
