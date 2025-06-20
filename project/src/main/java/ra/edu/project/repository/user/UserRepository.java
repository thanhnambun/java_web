package ra.edu.project.repository.user;

import ra.edu.project.entity.user.User;

public interface UserRepository {
    int save(User user);
    User getUserById(int id);
    void update(User user) ;
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
