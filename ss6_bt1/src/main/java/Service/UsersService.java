package Service;

import DAO.UsersDAO;
import Model.User;

import java.sql.SQLException;

public class UsersService {
    private final UsersDAO dao = new UsersDAO();

    public void register(User u) throws SQLException {
        dao.insertUser(u);
    }

    public User authenticate(String username, String password) throws SQLException {
        User u = dao.findByUsername(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}