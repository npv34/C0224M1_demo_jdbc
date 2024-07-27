package org.codegym.library.controllers;

import org.codegym.library.entity.Book;
import org.codegym.library.models.BookModel;
import org.codegym.library.services.BookService;

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.bookService = new BookService();
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
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renderListBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // get data from model
        List<Book> books = this.bookService.getAllBooks();
        // pass data to view
        request.setAttribute("books", books);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/books/list.jsp");
        requestDispatcher.forward(request, response);
    }
}
