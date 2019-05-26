package service;

import BusinessLogic.Util;
import dao.LocationDAO;
import entity.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationService extends Util implements LocationDAO {
    Connection connection = getConnection();
    @Override
    public void add(Location location) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO LOCATION(ID, LINE, SHELF, BOOK_ID)"+"VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, location.getId());
            preparedStatement.setLong(2, location.getLine());
            preparedStatement.setString(3, location.getShelf());
            preparedStatement.setLong(4, location.getBook_id());
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
    public List<Location> getAll() throws SQLException {
        List<Location> locationList = new ArrayList<>();
        String sql = "SELECT ID, LINE, SHELF, BOOK_ID";
        Statement statement =null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                Location location =new Location();
                location.setId(resultSet.getLong("ID"));
                location.setLine(resultSet.getLong("ROW"));
                location.setShelf(resultSet.getString("SHELF"));
                location.setBook_id(resultSet.getLong("BOOK_ID"));
                locationList.add(location);
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
        return locationList;
    }

    @Override
    public Location getById(Long id) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "SELECT ID, LINE, SHELF, BOOK_ID FROM LOCATION WHERE ID=?";
        Location location =new Location();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =preparedStatement.executeQuery();
            location.setId(resultSet.getLong("ID"));
            location.setLine(resultSet.getLong("ROW"));
            location.setShelf(resultSet.getString("SHELF"));
            location.setBook_id(resultSet.getLong("BOOK_ID"));
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
        return location;
    }

    @Override
    public void update(Location location) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "UPDATE LOCATION SET LINE=?,SHELF=?,BOOK_ID=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, location.getLine());
            preparedStatement.setString(2, location.getShelf());
            preparedStatement.setLong(3, location.getBook_id());
            preparedStatement.setLong(4, location.getId());
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
    public void delete(Location location) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM LOCATION WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, location.getId());
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
