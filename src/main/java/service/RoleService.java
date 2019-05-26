package service;

import BusinessLogic.Util;
import dao.RoleDAO;
import entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleService extends Util implements RoleDAO {
    Connection connection = getConnection();
    @Override
    public void add(Role role) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ROLE(ID, ACCESS) VALUES(?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, role.getId());
            preparedStatement.setString(2, role.getAccess());
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
    public List<Role> getAll() throws SQLException {
        List<Role> roleList = new ArrayList<>();
        String sql = "SELECT ID, ACCESS";
        Statement statement =null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                Role role =new Role();
                role.setId(resultSet.getLong("ID"));
                role.setAccess(resultSet.getString("ACCESS"));
                roleList.add(role);
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
        return roleList;
    }

    @Override
    public Role getById(Long id) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "SELECT ID, ACCESS FROM ROLE WHERE ID=?";
        Role role =new Role();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =preparedStatement.executeQuery();
            role.setId(resultSet.getLong("ID"));
            role.setAccess(resultSet.getString("ACCESS"));
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
        return role;
    }

    @Override
    public void update(Role role) throws SQLException {
        PreparedStatement preparedStatement =null;
        String sql = "UPDATE ROLE SET ACCESS=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getAccess());
            preparedStatement.setLong(2, role.getId());
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
    public void delete(Role role) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM ROLE WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, role.getId());
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
