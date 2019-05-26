package service;

import BusinessLogic.Util;
import dao.UserDAO;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends Util implements UserDAO {
    Connection connection = getConnection();

    @Override
    public void add(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO library.USER(ID, NAME, SURNAME, EMAIL, PASSWORD, PHONE) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setLong(6, user.getPhone());
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
    public List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, SURNAME, EMAIL,PASSWORD,PHONE";
        Statement statement =null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                User user =new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setSurname(resultSet.getString("SURNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPhone(resultSet.getLong("PHONE"));
                user.setPassword(resultSet.getString("PASSWORD"));
                userList.add(user);
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
        return userList;
    }

    @Override
    public User getById(Long id) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "SELECT ID, NAME, SURNAME, EMAIL,PASSWORD,PHONE FROM USER WHERE ID=?";
        User user =new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =preparedStatement.executeQuery();
            user.setId(resultSet.getLong("ID"));
            user.setName(resultSet.getString("NAME"));
            user.setSurname(resultSet.getString("SURNAME"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setPhone(resultSet.getLong("PHONE"));
            user.setPassword(resultSet.getString("PASSWORD"));
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
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "UPDATE USER SET NAME=?,SURNAME=?,EMAIL=?,PHONE=?,PASSWORD=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getPhone());
            preparedStatement.setLong(6, user.getId());
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
    public void delete(User user) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "DELETE FROM USER WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getId());
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
