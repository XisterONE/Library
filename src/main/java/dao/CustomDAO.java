package dao;

import entity.Custom;

import java.sql.SQLException;
import java.util.List;

public interface CustomDAO {
    //create
    void add(Custom custom) throws SQLException;
    //read
    List<Custom> getAll() throws SQLException;

    Custom getById(Long id) throws SQLException;
    //update
    void  update(Custom custom) throws SQLException;
    //delete
    void  delete(Custom custom) throws SQLException;
}
