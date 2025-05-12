package DAO;

import Model.Book;
import Util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibaryDAO {

    public void insertBook(Book b) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("{call sp_insert_book(?,?,?,?,?)}")) {
            cs.setString(1, b.getBookCode());
            cs.setString(2, b.getTitle());
            cs.setString(3, b.getAuthor());
            cs.setString(4, b.getCategory());
            cs.setInt   (5, b.getQuantity());
            cs.execute();
        }
    }

    public List<Book> selectAllBooks() throws SQLException {
        List<Book> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("{call sp_select_all_books()}");
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                list.add(new Book(
                        rs.getString("book_code"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("quantity")
                ));
            }
        }
        return list;
    }

    public Book selectBook(String code) throws SQLException {
        Book b = null;
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("{call sp_select_book(?)}")) {
            cs.setString(1, code);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    b = new Book(
                            rs.getString("book_code"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getInt("quantity")
                    );
                }
            }
        }
        return b;
    }

    public boolean updateBook(Book b) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("{call sp_update_book(?,?,?,?,?)}")) {
            cs.setString(1, b.getBookCode());
            cs.setString(2, b.getTitle());
            cs.setString(3, b.getAuthor());
            cs.setString(4, b.getCategory());
            cs.setInt   (5, b.getQuantity());
            return cs.executeUpdate() > 0;
        }
    }

    public boolean deleteBook(String code) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("{call sp_delete_book(?)}")) {
            cs.setString(1, code);
            return cs.executeUpdate() > 0;
        }
    }

    public List<Book> searchBooks(String keyword) throws SQLException {
        List<Book> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall("{call sp_search_books(?)}")) {
            cs.setString(1, "%" + keyword + "%");
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    list.add(new Book(
                            rs.getString("book_code"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getInt("quantity")
                    ));
                }
            }
        }
        return list;
    }
}