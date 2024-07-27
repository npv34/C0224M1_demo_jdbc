package org.codegym.library.models;

import org.codegym.library.databases.DatabaseConnect;
import org.codegym.library.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookModel {
    private Connection conn;

    public BookModel() {
        this.conn = DatabaseConnect.getConnection();
    }

    public ResultSet getBooks() throws SQLException {
        // Chuan bi cau lenh truy van
        String sql = "SELECT * FROM books";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        // thuc thi cau lenh truy van
        return preparedStatement.executeQuery();
    }

    public void destroyBook(int id) throws SQLException {
        // Chuan bi cau lenh truy van
        String sql = "DELETE FROM books WHERE id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        // set params
        preparedStatement.setInt(1, id);
        // thuc thi cau lenh truy van
        preparedStatement.execute();
    }
}
