package org.codegym.library.services;

import org.codegym.library.entity.Book;
import org.codegym.library.models.BookModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private BookModel bookModel;

    public BookService() {
        this.bookModel = new BookModel();
    }
    public List<Book> getAllBooks() throws SQLException {
        // Xu ly ket qua tra ve
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = this.bookModel.getBooks();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int category_id = resultSet.getInt("category_id");
            Book book =new Book(name, description, price, category_id);
            book.setId(id);
            books.add(book);
        }
        return books;
    }

    public void deleteBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.bookModel.destroyBook(id);
    }
}
