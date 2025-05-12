package Service;

import DAO.LibaryDAO;
import Model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private LibaryDAO dao = new LibaryDAO();

    public void addBook(Book b) throws SQLException {
        dao.insertBook(b);
    }

    public List<Book> getAll() throws SQLException {
        return dao.selectAllBooks();
    }

    public Book getByCode(String code) throws SQLException {
        return dao.selectBook(code);
    }

    public void updateBook(Book b) throws SQLException {
        dao.updateBook(b);
    }

    public void deleteBook(String code) throws SQLException {
        dao.deleteBook(code);
    }

    public List<Book> search(String kw) throws SQLException {
        return dao.searchBooks(kw);
    }
}

