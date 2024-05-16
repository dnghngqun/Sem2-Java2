package session10.model;

import session10.entity.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model <T extends Entity<?>> implements ModelDAO<T>{
    private final List<T> entities = new ArrayList<>();
    private static final Connection conn;
    private String SQL_CREATE_ORDER = "INSERT INTO Order_table(customerID, orderDate, totalAmount, status) VALUES (?,?,?,?)";
    private String SQL_ADD_ORDER_DETAIL = "INSERT INTO OrderDetail(orderID, productID, quantity, unitPrice, discount, totalPrice) VALUES (?,?,?,?,?,?)";
    private String SQL_DELETE_ORDER_DETAIL = "DELETE FROM OrderDetail WHERE id = ?";
    private String SQL_DELETE_ORDER = "DELETE FROM Order_table WHERE id = ?";
    private String SQL_GET_ORDERS_BY_CUSTOMER_ID = "SELECT o.id AS OrderID," +
            "o.orderDate," +
            "o.totalAmount," +
            "o.status " +
            "FROM Order_table o " +
            "WHERE o.customerID = ?";
    private String SQL_GET_ORDER_DETAIL_BY_CUSTOMER_ID = "SELECT od.id AS Order_Detail_ID, " +
            "c.name AS CustomerName, " +
            "p.ProductName, " +
            "od.quantity, " +
            "od.unitPrice, " +
            "od.discount, " +
            "od.totalPrice " +
            "FROM OrderDetail od " +
            "INNER JOIN Order_table o on od.orderID = o.id " +
            "INNER JOIN customers c on o.customerID = c.id " +
            "INNER JOIN Product p on od.productID = p.id " +
            "WHERE customerID = ?";
    private String SQL_GET_ALL_TOTAL_AMOUNT_BY_CUSTOMER_ID = "SELECT totalPrice FROM OrderDetail od " +
            "INNER JOIN Order_table Ot on od.orderID = Ot.id " +
            "WHERE customerID = ?";
    private String SQL_UPDATE_STATUS = "UPDATE Order_table SET status = ? WHERE id = ?";
    private String SQL_UPDATE_TOTAL_AMOUNT = "UPDATE Order_table SET totalAmount = ? WHERE id = ?";
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
    public boolean updateEntity(T entity) throws SQLException, IllegalAccessException {
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        sql.append(tableName);
        sql.append(" SET ");

        Field[] fields = entity.getClass().getDeclaredFields();
        for(int i = 0; i < fields.length; i++) {
            sql.append(fields[i].getName());
            sql.append(" = ? ");
            if(i < fields.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(" WHERE id = ?");

        pstm = conn.prepareStatement(sql.toString());
        int index = 1;
        for(Field field : fields) {
            field.setAccessible(true);
            pstm.setObject(index++, field.get(entity));
        }
        pstm.setObject(index, entity.getId());
        int rs = pstm.executeUpdate();
        if(rs > 0) {
            System.out.println("Update success!");
            return true;
        }
        System.out.println("Update failed!");
        return false;
    }

    @Override
    public boolean deleteEntity(T entity) throws SQLException {
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(tableName);
        sql.append(" WHERE id = ?");
        pstm = conn.prepareStatement(sql.toString());
        pstm.setObject(1, entity.getId());
        int rs = pstm.executeUpdate();
        if (rs > 0) {
            System.out.println("Delete success!");
            return true;
        }
        return false;
    }

//    @Override
//    public List<T> getEntityById(T entity) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        if(!entities.isEmpty()) entities.clear();
//        String tableName = getTableName(entity.getClass());
//        StringBuilder sql = new StringBuilder();
//        sql.append("SELECT * FROM ");
//        sql.append(tableName);
//        sql.append("WHERE id = ?");
//        pstm = conn.prepareStatement(sql.toString());
//        pstm.setObject(1, entity.getId());
//        ResultSet rs = pstm.executeQuery();
//        while (rs.next()) {
//            try {
//                distinguishClassResultSet(rs, entity);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return entities;
//    }


    @Override
    public T getEntityById(T entity) throws SQLException {
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(tableName);
        sql.append(" WHERE id = ?");
        pstm = conn.prepareStatement(sql.toString());
        pstm.setObject(1, entity.getId());
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            try {
                return distinguishClassResultSet(rs, entity.getClass());
            } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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
                entities.add(distinguishClassResultSet(rs, entity.getClass()));
            } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return entities;
    }

    @Override
    public List<T> getAllEntitiesByName(T entity) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        if(!entities.isEmpty()) entities.clear();
        String tableName = getTableName(entity.getClass());
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(tableName);

        Field field = null;
        try {
            field = entity.getClass().getDeclaredField("name");

        } catch (Exception e) {
            try {
                field = entity.getClass().getDeclaredField("ProductName");
            } catch (NoSuchFieldException ex) {
                System.out.println("Field not found!");
            }
        }
        sql.append(" WHERE ");
        sql.append(field.getName());
        sql.append(" = ?");
        pstm = conn.prepareStatement(sql.toString());
        if (field != null) {
            field.setAccessible(true);
            pstm.setObject(1, field.get(entity));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                entities.add(distinguishClassResultSet(rs, entity.getClass()));
            }
        }
        return entities;
    }

//    private T distinguishClassResultSet(ResultSet rs, Class<?> entityClass) throws SQLException, IllegalAccessException {
//        return switch (entityClass.getSimpleName()) {
//            case "Customer" -> (T) setValueCustomer(rs);
//            case "Product" -> (T) setValueProduct(rs);
//            case "OrderDetail" -> (T) setValueOrderDetail(rs);
//            case "Order" -> (T) setValueOrder(rs);
//            default -> null;
//        };
//    }
    private T distinguishClassResultSet(ResultSet rs, Class<?> entityClass) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        T newEntity = (T) entityClass.newInstance();
        //get field id in extend class
        Field fieldID = entityClass.getSuperclass().getDeclaredField("id");
        fieldID.setAccessible(true);
        fieldID.set(newEntity, rs.getInt("id"));
        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);

            field.set(newEntity, rs.getObject(field.getName()));
        }
        return newEntity;
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
//
//    public Customer setValueCustomer(ResultSet rs) throws SQLException {
//        Customer customer = new Customer();
//        customer.setId(rs.getInt("id"));
//        customer.setName(rs.getString("name"));
//        customer.setAddress(rs.getString("address"));
//        customer.setEmail(rs.getString("email"));
//        return customer;
//    }
//
//    public Product setValueProduct(ResultSet rs) throws SQLException {
//        Product product = new Product();
//        product.setId(rs.getInt("id"));
//        product.setProductName(rs.getString("ProductName"));
//        product.setPrice(rs.getDouble("Price"));
//        product.setDescription(rs.getString("description"));
//        return product;
//    }
//
//    public Order setValueOrder(ResultSet rs) throws SQLException {
//        Order order = new Order();
//        order.setId(rs.getInt("id"));
//        order.setCustomerId(rs.getInt("customerID"));
//        order.setOrderDate(rs.getDate("orderDate"));
//        order.setTotalAmount(rs.getDouble("totalAmount"));
//        order.setStatus(rs.getString("status"));
//        return order;
//    }
//
//    public OrderDetail setValueOrderDetail(ResultSet rs) throws SQLException {
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setId(rs.getInt("id"));
//        orderDetail.setOrderId(rs.getInt("orderID"));
//        orderDetail.setProductId(rs.getInt("productID"));
//        orderDetail.setQuantity(rs.getInt("quantity"));
//        orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
//        orderDetail.setDiscount(rs.getDouble("discount"));
//        orderDetail.setTotalPrice(rs.getDouble("totalPrice"));
//        return orderDetail;
//    }

    public void addOrder(Order order, List<OrderDetail> orderDetail) throws SQLException {
        conn.setAutoCommit(false);
        pstm = conn.prepareStatement(SQL_CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
        pstm.setInt(1, order.getCustomerId());
        pstm.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstm.setDouble(3, order.getTotalAmount());
        pstm.setString(4, order.getStatus());
        pstm.executeUpdate();
        ResultSet rs = pstm.getGeneratedKeys();
        int orderId = -1;
        if (rs.next()) {
            orderId = rs.getInt(1);
        }
        if (orderId != -1) {
            //insert order detail
            pstm = conn.prepareStatement(SQL_ADD_ORDER_DETAIL);
            double totalAmount = 0;
            for (OrderDetail od : orderDetail) {
                totalAmount += od.getTotalPrice();
                pstm.setInt(1, orderId);
                pstm.setInt(2, od.getProductId());
                pstm.setInt(3, od.getQuantity());
                pstm.setDouble(4, od.getUnitPrice());
                pstm.setDouble(5, od.getDiscount());
                pstm.setDouble(6, od.getTotalPrice());
                pstm.addBatch();
            }
            pstm.executeBatch();
            System.out.println("Total amount: " + totalAmount);
            pstm = conn.prepareStatement(SQL_UPDATE_TOTAL_AMOUNT);
            pstm.setDouble(1, totalAmount);
            pstm.setInt(2, orderId);
            pstm.executeUpdate();
        }
        conn.commit();
    }

    public boolean deleteOrder(int id) throws SQLException {
        conn.setAutoCommit(false);
        pstm = conn.prepareStatement(SQL_DELETE_ORDER_DETAIL);
        pstm.setInt(1, id);
        int result = pstm.executeUpdate();
        if(result > 0){
            pstm = conn.prepareStatement(SQL_DELETE_ORDER);
            pstm.setInt(1, id);
            int rs = pstm.executeUpdate();
            if (rs > 0) {
                conn.commit();
                return true;
            }else {
                conn.rollback();
                return false;
            }
        }
        return false;
    }

    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        pstm = conn.prepareStatement(SQL_GET_ORDERS_BY_CUSTOMER_ID);
        pstm.setInt(1, customerId);
        ResultSet rs = pstm.executeQuery();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("OrderID"));
            order.setOrderDate(rs.getDate("orderDate"));
            order.setTotalAmount(rs.getDouble("totalAmount"));
            order.setStatus(rs.getString("status"));
            list.add(order);
        }
        return list;
    }

    public List<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException {
        pstm = conn.prepareStatement(SQL_GET_ORDER_DETAIL_BY_CUSTOMER_ID);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        List<OrderDetail> list = new ArrayList<>();
        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(rs.getInt("Order_Detail_ID"));
            orderDetail.setCustomerName(rs.getString("CustomerName"));
            orderDetail.setProductName(rs.getString("ProductName"));
            orderDetail.setQuantity(rs.getInt("quantity"));
            orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
            orderDetail.setDiscount(rs.getDouble("discount"));
            orderDetail.setTotalPrice(rs.getDouble("totalPrice"));
            list.add(orderDetail);
        }
        return list;
    }

    public double getTotalPriceByCustomerId(int id) throws SQLException {
        pstm = conn.prepareStatement(SQL_GET_ALL_TOTAL_AMOUNT_BY_CUSTOMER_ID);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        double total = 0;
        while (rs.next()) {
            total += rs.getDouble("totalPrice");
        }
        return total;
    }
    public boolean updateOrderStatus(int orderId, String status) throws SQLException {
        pstm = conn.prepareStatement(SQL_UPDATE_STATUS);
        pstm.setString(1, status);
        pstm.setInt(2, orderId);
        int rowAffected = pstm.executeUpdate();
        return rowAffected > 0;
    }

}
