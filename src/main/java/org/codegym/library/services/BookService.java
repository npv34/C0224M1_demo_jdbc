package org.codegym.library.services;

import org.codegym.library.entity.Book;
import org.codegym.library.entity.Category;
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
    public List<Book> getAllBooks(HttpServletRequest request) throws SQLException {
        // Xu ly ket qua tra ve
        List<Book> books = new ArrayList<>();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        ResultSet resultSet = null;

        if (page == null || limit == null) {
            resultSet = this.bookModel.getBooks();
        } else {
            int intPage = Integer.parseInt(page);
            int intLimit = Integer.parseInt(limit);
            // vd page = 2 & limit = 5 -> lay phan tu tu 5 - 10
            // vd page = 3 & limit = 5 -> lay phan tu tu 15 - 20
            int offset = (intPage - 1 ) * intLimit;
            resultSet = this.bookModel.getBooksByOffset(offset, intLimit);
        }

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int category_id = resultSet.getInt("category_id");
            String nameCategory = resultSet.getString("category_name");

            Book book = new Book(name, description, price, category_id);
            book.setId(id);
            // Them category vao book
            Category category = new Category(nameCategory);
            category.setId(category_id);

            book.setCategory(category);
            books.add(book);
        }
        return books;
    }

    public int getBookCount() throws SQLException {
        ResultSet resultSet = this.bookModel.getBooks();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        return count;
    }


    public void deleteBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.bookModel.destroyBook(id);
    }

    public void createBook(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String category_id = request.getParameter("category_id");

        int priceInt = Integer.parseInt(price);

        Book book = new Book(name, description, priceInt, Integer.parseInt(category_id));
        this.bookModel.store(book);
    }

    public List<Book> searchBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet resultSet = this.bookModel.search(keyword);
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int category_id = resultSet.getInt("category_id");
            String nameCategory = resultSet.getString("category_name");

            Book book = new Book(name, description, price, category_id);
            book.setId(id);
            // Them category vao book
            Category category = new Category(nameCategory);
            category.setId(category_id);

            book.setCategory(category);
            books.add(book);
        }
        return books;
    }

    public Book findByID(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        // Tim kiem book theo id
        ResultSet resultSet = this.bookModel.getBookById(id);
        Book book = null;
        while (resultSet.next()) {
            int idBook = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            int category_id = resultSet.getInt("category_id");
            String nameCategory = resultSet.getString("category_name");

            book = new Book(name, description, price, category_id);
            book.setId(idBook);
            // Them category vao book
            Category category = new Category(nameCategory);
            category.setId(category_id);

            book.setCategory(category);
        }

        return book;
    }

    public void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        // lay data from request
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String category_id = request.getParameter("category_id");

        int priceInt = Integer.parseInt(price);

        // tao book moi
        Book book = new Book(name, description, priceInt, Integer.parseInt(category_id));
        book.setId(id);

        // cap nhat book vao database
        this.bookModel.update(book);

    }
}
