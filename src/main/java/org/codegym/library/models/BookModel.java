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
        String sql = "SELECT books.*, categories.name as category_name FROM books JOIN categories ON books.category_id = categories.id";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        // thuc thi cau lenh truy van
        return preparedStatement.executeQuery();
    }

    public ResultSet getBooksByOffset(int offset, int limit) throws SQLException {
        // Chuan bi cau lenh truy van
        String sql = "SELECT books.*, categories.name as category_name FROM books JOIN categories ON books.category_id = categories.id LIMIT ? OFFSET ? ";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, limit);
        preparedStatement.setInt(2, offset);
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

    public void store(Book book) throws SQLException {
        String sql = "INSERT INTO books(name, description, price, category_id) VALUE (?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getDescription());
        preparedStatement.setInt(3, book.getPrice());
        preparedStatement.setInt(4, book.getCategory_id());
        preparedStatement.execute();
    }

    public ResultSet search(String name) throws SQLException {
        // Chuan bi cau lenh truy van
        String sql = "SELECT books.*, categories.name as category_name FROM books JOIN categories ON books.category_id = categories.id WHERE books.name LIKE ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        // set params
        preparedStatement.setString(1, "%" + name + "%");
        // thuc thi cau lenh truy van
        return preparedStatement.executeQuery();
    }

    public ResultSet getBookById(int id) throws SQLException {
        String sql = "SELECT books.*, categories.name as category_name FROM books JOIN categories ON books.category_id = categories.id WHERE books.id = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void update(Book book) throws SQLException {
        String sql = "UPDATE books SET name =?, description =?, price =?, category_id =? WHERE id =?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getDescription());
        preparedStatement.setInt(3, book.getPrice());
        preparedStatement.setInt(4, book.getCategory_id());
        preparedStatement.setInt(5, book.getId());
        preparedStatement.execute();
    }

}
