package service;

import BusinessLogic.Util;
import dao.BookDAO;
import entity.Book;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService extends Util implements BookDAO {
    Connection connection = getConnection();
    @Override
    public void add(Book book) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO BOOK(ID, NAME, AUTHOR, DESCRIPTION, ATTRIBUTES)"+"VALUES(?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, book.getId());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setString(5, book.getAttributes());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT ID, NAME, AUTHOR, DESCRIPTION, ATRIBUTES";
        Statement statement =null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getLong("ID"));
                book.setName(resultSet.getString("NAME"));
                book.setAuthor(resultSet.getString("AUTHOR"));
                book.setDescription(resultSet.getString("DESCRIPTION"));
                book.setAttributes(resultSet.getString("ATTRIBUTES"));
                bookList.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return bookList;
    }

    @Override
    public Book getById(Long id) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "SELECT ID, NAME, AUTHOR, DESCRIPTION, ATRIBUTES FROM BOOK WHERE ID=?";
        Book book =new Book();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =preparedStatement.executeQuery();
            book.setId(resultSet.getLong("ID"));
            book.setName(resultSet.getString("NAME"));
            book.setAuthor(resultSet.getString("AUTHOR"));
            book.setDescription(resultSet.getString("DESCRIPTION"));
            book.setAttributes(resultSet.getString("ATTRIBUTES"));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return book;
    }

    @Override
    public void update(Book book) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "UPDATE BOOK SET NAME=?,AUTHOR=?,DESCRIPTION=?,ATTRIBUTES=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setString(4, book.getAttributes());
            preparedStatement.setLong(5, book.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void delete(Book book) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "DELETE FROM BOOK WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, book.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
}
