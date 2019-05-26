package dao;

import entity.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
    //create
    void add(Role role) throws SQLException;
    //read
    List<Role> getAll() throws SQLException;

    Role getById(Long id) throws SQLException;
    //update
    void  update(Role role) throws SQLException;
    //delete
    void  delete(Role role) throws SQLException;
}
