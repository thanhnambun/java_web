package DAO;

import Model.User;
import Util.ConnectionDB;

import java.sql.*;

public class UsersDAO {

    public void insertUser(User u) throws SQLException {
        try (
                Connection conn = ConnectionDB.getConnection();
                CallableStatement cs = conn.prepareCall("{call sp_insert_user(?,?,?,?)}")
        ) {
            cs.setString(1, u.getUsername());
            cs.setString(2, u.getPassword());
            cs.setString(3, u.getEmail());
            cs.setString(4, u.getPhone());
            cs.execute();
        }
    }

    public User findByUsername(String username) throws SQLException {
        User u = null;
        try (
                Connection conn = ConnectionDB.getConnection();
                CallableStatement cs = conn.prepareCall("{call sp_select_user_by_username(?)}")
        ) {
            cs.setString(1, username);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    u = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("phone")
                    );
                }
            }
        }
        return u;
    }
}