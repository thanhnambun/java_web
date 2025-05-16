
package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/ss9_java";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "265205";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return conn;
    }
    public static void closeConnection(Connection conn, CallableStatement callSt){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.fillInStackTrace();
            }
        }
        if(callSt != null){
            try {
                callSt.close();
            } catch (SQLException e) {
                e.fillInStackTrace();
            }
        }

    }
}
