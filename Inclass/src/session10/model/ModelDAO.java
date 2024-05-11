package session10.model;

import java.sql.SQLException;
import java.util.List;

public interface ModelDAO<T> {

    void addEntity(String tableName, List<Object> values) throws SQLException;
    void updateEntity(String tableName, int id, List<Object> values) throws SQLException;
    void deleteEntity(String tableName, int id) throws SQLException;
    List<Object> getEntityById(String tableName, int id) throws SQLException;
    List<Object> getAllEntities(String tableName) throws SQLException;
}
