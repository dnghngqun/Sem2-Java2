package session10.model;

import session10.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface ModelDAO<T> {

    void addEntity(T entity) throws SQLException, IllegalAccessException;
    void updateEntity(T entity) throws SQLException, IllegalAccessException;
    boolean deleteEntity(T entity ) throws SQLException;
    List<T> getEntityById(T entity) throws SQLException;
    List<T> getAllEntities(T entity) throws SQLException;
}
