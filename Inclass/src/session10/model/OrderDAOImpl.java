package session10.model;

import session10.entity.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO{
    private static final Connection conn;
    private PreparedStatement pstm = null;
    private String SQL_CREATE_ORDER = "INSERT INTO Order_table(id, customerID, orderDate, totalAmount, status) VALUES (?,?,?,?,?)";
    private String SQL_UPDATE_ORDER = "UPDATE Order_table SET customerID = ?, orderDate = ?, totalAmount = ?, status = ? WHERE id = ?";
    private String SQL_DELETE_ORDER = "DELETE FROM Order_table WHERE id = ?";
    private String SQL_GET_ALL_ORDERS = "SELECT o.id AS OrderID," +
                                        "customerID," +
                                        "c.name AS CustomerName," +
                                        "o.orderDate," +
                                        "o.totalAmount," +
                                        "o.status" +
                                        "FROM Order_table o" +
                                        "INNER JOIN customers c on o.customerID = c.id";
    private String SQL_GET_ORDER_BY_ID = "SELECT o.id AS OrderID," +
                                        "customerID," +
                                        "c.name AS CustomerName," +
                                        "o.orderDate," +
                                        "o.totalAmount," +
                                        "o.status" +
                                        "FROM Order_table o" +
                                        "INNER JOIN customers c on o.customerID = c.id" +
                                        "WHERE o.id = ?";
    private String SQL_GET_ORDERS_BY_CUSTOMER_ID = "SELECT o.id AS OrderID," +
                                        "customerID," +
                                        "c.name AS CustomerName," +
                                        "o.orderDate," +
                                        "o.totalAmount," +
                                        "o.status" +
                                        "FROM Order_table o" +
                                        "INNER JOIN customers c on o.customerID = c.id" +
                                        "WHERE o.customerID = ?";
    private String SQL_UPDATE_STATUS = "UPDATE Order_table SET status = ? WHERE id = ?";
    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOrder(Order order) throws SQLException {
        pstm = conn.prepareStatement(SQL_CREATE_ORDER);
        pstm.setInt(1, order.getId());
        pstm.setInt(2, order.getCustomerId());
        Date date = new Date(order.getOrderDate().getTime());//date định dạng java.sql
        pstm.setDate(3, date);
        pstm.setDouble(4, order.getTotalAmount());
        pstm.setString(5, order.getStatusString());
        pstm.executeUpdate();
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        pstm = conn.prepareStatement(SQL_UPDATE_ORDER);
        pstm.setInt(1, order.getCustomerId());
        pstm.setDate(2, new Date(order.getOrderDate().getTime()));
        pstm.setDouble(3, order.getTotalAmount());
        pstm.setString(4, order.getStatusString());
        pstm.setInt(5, order.getId());
        pstm.executeUpdate();
    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        pstm = conn.prepareStatement(SQL_DELETE_ORDER);
        pstm.setInt(1, id);

        int result = pstm.executeUpdate();
        if(result > 0) return true;
        return false;
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        pstm = conn.prepareStatement(SQL_GET_ORDER_BY_ID);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("OrderID"));
            order.setCustomerId(rs.getInt("customerID"));
            order.setOrderDate(rs.getDate("orderDate"));
            order.setTotalAmount(rs.getDouble("totalAmount"));
            order.setStatusString(rs.getString("status"));
            return order;
        }
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        ArrayList<Order> list = new ArrayList<>();
        pstm = conn.prepareStatement(SQL_GET_ORDERS_BY_CUSTOMER_ID);
        pstm.setInt(1, customerId);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("OrderID"));
            order.setCustomerId(rs.getInt("customerID"));
            order.setOrderDate(rs.getDate("orderDate"));
            order.setTotalAmount(rs.getDouble("totalAmount"));
            order.setStatusString(rs.getString("status"));
            list.add(order);
        }

        return list;
    }

    @Override
    public ArrayList<Order> getAllOrders() throws SQLException {
        ArrayList<Order> list = new ArrayList<>();
        pstm = conn.prepareStatement(SQL_GET_ALL_ORDERS);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Order order = new Order();
            order.setId(rs.getInt("OrderID"));
            order.setCustomerId(rs.getInt("customerID"));
            order.setOrderDate(rs.getDate("orderDate"));
            order.setTotalAmount(rs.getDouble("totalAmount"));
            order.setStatusString(rs.getString("status"));
            list.add(order);
        }
        return list;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) throws SQLException {
        pstm = conn.prepareStatement(SQL_UPDATE_STATUS);
        pstm.setString(1, status);
        pstm.setInt(2, orderId);
        int rowAffected = pstm.executeUpdate();
        if(rowAffected > 0) {
            return true;
        }
        return false;
    }

}
