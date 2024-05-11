package session10.model;

import session10.entity.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model <T extends Entity<?>>  {
    private List<T> entities = new ArrayList<>();
    private static final Connection conn;

    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement pstm = null;

    public void addEntity(T entity) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity);
        Field[] fields = entity.getClass().getDeclaredFields();//lấy toàn bộ fields
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append(" (");
        for (int i = 0; i < fields.length; i++) {
            sql.append(fields[i].getName());
            if (i < fields.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");
        sql.append(" VALUES (");
        for (int i = 0; i < fields.length; i++) {
            sql.append("?");
            if (i < fields.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        pstm = conn.prepareStatement(sql.toString());
        int index = 1;
        for(Field field : fields) {
            field.setAccessible(true);
            pstm.setObject(index++, field.get(entity));
        }

        pstm.executeUpdate();
        System.out.println("Insert success!");
    }

    private String getTableName(T entity) throws SQLException {
        if(entity instanceof Customer) return "customers";

        if(entity instanceof Product) return "Product";

        if(entity instanceof OrderDetail) return "OrderDetail";

        if (entity instanceof Order) return "Order_table";

        return null;
    }

    public List<T> getAllEntities() throws SQLException {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public List<T> getEntities() {
        return entities;
    }


}
//add update delete getByID getAll