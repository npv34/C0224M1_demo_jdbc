package org.codegym.library.models;

import org.codegym.library.databases.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryModel {

    private Connection conn;

    public CategoryModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getAllCategories() throws SQLException {
        String sql = "SELECT * FROM categories";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
}
