package session10.model;

import session10.entity.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model <T extends Entity<?>> implements ModelDAO<T>{
    private final List<T> entities = new ArrayList<>();
    private static final Connection conn;

    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement pstm = null;


    @Override
    public void addEntity(T entity) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append(" (");
        Field[] fields = entity.getClass().getDeclaredFields();//lấy toàn bộ fields
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
            field.setAccessible(true);//cho phép truy cập các trường private, protected của class
            pstm.setObject(index++, field.get(entity));//get(entity) là lấy giá trị của các trường
        }

        pstm.executeUpdate();
        System.out.println("Insert success!");
    }

    @Override
    public void updateEntity(T entity) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        sql.append(tableName);
        sql.append("SET ");

        Field[] fields = entity.getClass().getDeclaredFields();
        for(int i = 0; i < fields.length; i++) {
            sql.append(fields[i].getName());
            sql.append(" = ? ");
            if(i < fields.length - 1) {
                sql.append(", ");
            }
        }
        sql.append("WHERE id = ?");

        pstm = conn.prepareStatement(sql.toString());
        int index = 1;
        for(Field field : fields) {
            field.setAccessible(true);
            pstm.setObject(index++, field.get(entity));
        }
        pstm.setObject(index, entity.getId());
        System.out.println("Update success!" );
    }

    @Override
    public boolean deleteEntity(T entity) throws SQLException {
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(tableName);
        sql.append("WHERE id = ?");
        pstm = conn.prepareStatement(sql.toString());
        pstm.setObject(1, entity.getId());
        int rs = pstm.executeUpdate();
        if (rs > 0) {
            System.out.println("Delete success!");
            return true;
        }
        return false;
    }

    @Override
    public List<T> getEntityById(T entity) throws SQLException {
        if(!entities.isEmpty()) entities.clear();
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(tableName);
        sql.append("WHERE id = ?");
        pstm = conn.prepareStatement(sql.toString());
        pstm.setObject(1, entity.getId());
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            try {
                distinguishClass(rs, entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return entities;
    }

    @Override
    public List<T> getAllEntities(T entity) throws SQLException {
        if(!entities.isEmpty()) entities.clear();
//        List<T> list = new ArrayList<>();
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(tableName);
        pstm = conn.prepareStatement(sql.toString());
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            try {
                distinguishClass(rs, entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return entities;
    }

    private void distinguishClass(ResultSet rs, T entity) throws SQLException, IllegalAccessException {
        if (entity.getClass().getSimpleName().equals("Customer"))
            entities.add((T) setValueCustomer(rs));
        if (entity.getClass().getSimpleName().equals("Product"))
            entities.add((T) setValueProduct(rs));
        if (entity.getClass().getSimpleName().equals("OrderDetail"))
            entities.add((T) setValueOrderDetail(rs));
        if (entity.getClass().getSimpleName().equals("Order"))
            entities.add((T) setValueOrder(rs));
    }

    private String getTableName(Class<?> entityClass) {
        String className = entityClass.getSimpleName();
        //-> để thay thế return trong switch-case
        return switch (className) {
            case "Customer" -> "customers";
            case "Product" -> "Product";
            case "OrderDetail" -> "OrderDetail";
            case "Order" -> "Order_table";
            default -> null;
        };

    }

    public Customer setValueCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setAddress(rs.getString("address"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }

    public Product setValueProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setProductName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        return product;
    }

    public Order setValueOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
       order.setCustomerId(rs.getInt("customer_id"));
        order.setOrderDate(rs.getDate("order_date"));
        order.setTotalAmount(rs.getDouble("total_amount"));
        order.setStatusString(rs.getString("status"));
        return order;
    }

    public OrderDetail setValueOrderDetail(ResultSet rs) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(rs.getInt("id"));
        orderDetail.setOrderId(rs.getInt("order_id"));
        orderDetail.setProductId(rs.getInt("product_id"));
        orderDetail.setQuantity(rs.getInt("quantity"));
        orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
        orderDetail.setDiscount(rs.getDouble("discount"));
        orderDetail.setTotalPrice(rs.getDouble("totalPrice"));
        return orderDetail;
    }



}
