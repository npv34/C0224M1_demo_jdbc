package org.codegym.library.controllers;

import org.codegym.library.entity.Book;
import org.codegym.library.entity.Category;
import org.codegym.library.models.BookModel;
import org.codegym.library.services.BookService;
import org.codegym.library.services.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookController", urlPatterns = {"/books/*"})
public class BookController extends HttpServlet {
    private BookService bookService;
    private CategoryService categoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.bookService = new BookService();
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if (url == null || url.equals("/")) {
                this.renderListBooks(req, resp);
            }

            switch (url) {
                case "/delete":
                    this.bookService.deleteBooks(req, resp);
                    resp.sendRedirect("/books");
                    break;
                case "/create":
                    this.renderPageCreate(req, resp);
                    break;
                case "/search":
                    this.renderSearchPage(req, resp);
                    break;
                case "/update":
                    this.renderPageUpdate(req, resp);
                    break;
                default:

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        switch (url) {
            case "/create":
                try {
                    this.bookService.createBook(req, resp);
                    resp.sendRedirect("/books");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/update":
                try {
                    this.bookService.updateBook(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/books");
                break;
        }
    }

    public void renderListBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // get data from model
        List<Book> books = this.bookService.getAllBooks(request);
        request.setAttribute("books", books);
        int totalBooks = this.bookService.getBookCount();
        request.setAttribute("totalBooks", totalBooks);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/books/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // lay danh sach the loai trong csdl
        List<Category> categories = this.categoryService.getAllCategories();
        // pass data to view
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/books/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderSearchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Book> books = this.bookService.searchBooks(request, response);
        request.setAttribute("books", books);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/books/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void renderPageUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        Book book = this.bookService.findByID(request);
        // lay danh sach the loai trong csdl
        List<Category> categories = this.categoryService.getAllCategories();
        // pass data to view
        request.setAttribute("book", book);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/books/update.jsp");
        requestDispatcher.forward(request, response);
    }
}
