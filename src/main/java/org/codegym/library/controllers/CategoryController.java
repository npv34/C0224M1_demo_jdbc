package org.codegym.library.controllers;

import org.codegym.library.entity.Category;
import org.codegym.library.services.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryController", urlPatterns = {"/categories/*"})
public class CategoryController extends HttpServlet {
    private CategoryService categoryService;
    @Override
    public void init() throws ServletException {
         this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            if (url == null || url.equals("/categories")) {
                // render list categories
                this.renderListCategoryPage(req, resp);
            }

            switch (url) {
                case "/create":
                    // render create category page
                    this.renderCreateCategoryPage(req, resp);
                    break;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        switch (url) {
            case "/create":
                try {
                    this.categoryService.createCategory(req, resp);
                    resp.sendRedirect("/categories");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    protected void renderListCategoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Category> categories = this.categoryService.getAllCategories();
        // pass data to view
        request.setAttribute("categories", categories);
        // forward request to view
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/categories/list.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void renderCreateCategoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/categories/create.jsp");
        requestDispatcher.forward(request, response);
    }
}
