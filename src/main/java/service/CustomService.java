package service;

import BusinessLogic.Util;
import dao.CustomDAO;
import entity.Custom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomService extends Util implements CustomDAO {
    Connection connection = getConnection();
    @Override
    public void add(Custom custom) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO CUSTOM (ID, LOCATION_ID, USER_ID, TIME_USAGE)"+"VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, custom.getId());
            preparedStatement.setLong(2, custom.getPosition_id());
            preparedStatement.setLong(3, custom.getUser_id());
            preparedStatement.setDate(4, (Date) custom.getTime_usage());
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
    public List<Custom> getAll() throws SQLException {
        List<Custom> customList = new ArrayList<>();
        String sql = "SELECT ID, LOCATION_ID, USER_ID, TIME_USAGE";
        Statement statement =null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                Custom custom =new Custom();
                custom.setId(resultSet.getLong("ID"));
                custom.setPosition_id(resultSet.getLong("POSITION_ID"));
                custom.setUser_id(resultSet.getLong("USER_ID"));
                custom.setTime_usage(resultSet.getDate("TIME_USAGE"));
                customList.add(custom);
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
        return customList;
    }

    @Override
    public Custom getById(Long id) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "SELECT ID, LOCATION_ID, USER_ID, TIME_USAGE FROM CUSTOM WHERE ID=?";
        Custom custom =new Custom();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =preparedStatement.executeQuery();
            custom.setId(resultSet.getLong("ID"));
            custom.setPosition_id(resultSet.getLong("POSITION_ID"));
            custom.setUser_id(resultSet.getLong("USER_ID"));
            custom.setTime_usage(resultSet.getDate("TIME_USAGE"));
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
        return custom;
    }

    @Override
    public void update(Custom custom) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "UPDATE CUSTOM SET LOCATION_ID=?,USER_ID=?,TIME_USAGE=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, custom.getPosition_id());
            preparedStatement.setLong(2, custom.getUser_id());
            preparedStatement.setDate(3, (Date) custom.getTime_usage());
            preparedStatement.setLong(4, custom.getId());
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
    public void delete(Custom custom) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM CUSTOM WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, custom.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
