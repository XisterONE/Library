package dao;

import entity.Location;

import java.sql.SQLException;
import java.util.List;

public interface LocationDAO {
    //create
    void add(Location location) throws SQLException;
    //read
    List<Location> getAll() throws SQLException;

    Location getById(Long id) throws SQLException;
    //update
    void  update(Location location) throws SQLException;
    //delete
    void  delete(Location location) throws SQLException;
}
