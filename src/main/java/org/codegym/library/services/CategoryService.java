package org.codegym.library.services;

import org.codegym.library.entity.Category;
import org.codegym.library.models.CategoryModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private CategoryModel categoryModel;

    public CategoryService() {
        this.categoryModel = new CategoryModel();
    }


    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        ResultSet resultSet = this.categoryModel.getAllCategories();
        // xu ly du lieu tra ve
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Category category = new Category(name);
            category.setId(id);
            categories.add(category);
        }
        return categories;
    }

    public void createCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String name = request.getParameter("name");
        Category category = new Category(name);
        this.categoryModel.store(category);
    }
}
