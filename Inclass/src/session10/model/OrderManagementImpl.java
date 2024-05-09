package session10.model;

import session10.entity.Order;
import session10.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderManagementImpl implements OrderManagementDAO{
    private static final Connection conn;

    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement pstm = null;
    ResultSet rs = null;
    private String SQL_CREATE_ORDER = "INSERT INTO Order_table(customerID, orderDate, totalAmount, status) VALUES (?,?,?,?)";
    private String SQL_UPDATE_ORDER = "UPDATE Order_table SET customerID = ?, totalAmount = ?, status = ? WHERE id = ?";
    private String SQL_DELETE_ORDER = "DELETE FROM Order_table WHERE id = ?";
    private String SQL_GET_ALL_ORDERS = "SELECT o.id AS OrderID, customerID, c.name AS CustomerName," +
            "o.orderDate," +
            "o.totalAmount," +
            "o.status " +
            "FROM Order_table o " +
            "INNER JOIN customers c on o.customerID = c.id";
    private String SQL_GET_ORDER_BY_ID = "SELECT o.id AS OrderID," +
            "customerID," +
            "c.name AS CustomerName," +
            "o.orderDate," +
            "o.totalAmount," +
            "o.status " +
            "FROM Order_table o " +
            "INNER JOIN customers c on o.customerID = c.id " +
            "WHERE o.id = ?";
    private String SQL_GET_ORDERS_BY_CUSTOMER_ID = "SELECT o.id AS OrderID," +
            "customerID," +
            "c.name AS CustomerName," +
            "o.orderDate," +
            "o.totalAmount," +
            "o.status " +
            "FROM Order_table o " +
            "INNER JOIN customers c on o.customerID = c.id " +
            "WHERE o.customerID = ?";
    private String SQL_UPDATE_STATUS = "UPDATE Order_table SET status = ? WHERE id = ?";
    private String SQL_DELETE_ORDER_BY_CUSTOMER_ID = "DELETE FROM Order_table WHERE customerID = ?";
    private String SQL_GET_ORDER_DETAIL_BY_ORDER_ID = "SELECT od.id AS Order_Detail_ID, " +
            "c.name AS CustomerName, " +
            "p.ProductName, " +
            "od.quantity, " +
            "od.unitPrice, " +
            "od.discount, " +
            "od.totalPrice " +
            "FROM OrderDetail od " +
            "INNER JOIN Order_table o on od.orderID = o.id " +
            "INNER JOIN customers c on o.customerID = c.id " +
            "INNER JOIN Product p on od.productID = p.ProductID " +
            "WHERE orderID = ?";
    private String SQL_ADD_ORDER_DETAIL = "INSERT INTO OrderDetail(orderID, productID, quantity, unitPrice, discount, totalPrice) VALUES (?,?,?,?,?,?,?)";
    private String SQL_UPDATE_ORDER_DETAIL = "UPDATE OrderDetail SET orderID = ?, productID = ?, quantity = ?, unitPrice = ?, discount = ?, totalPrice = ? WHERE id = ?";
    private String SQL_DELETE_ORDER_DETAIL = "DELETE FROM OrderDetail WHERE id = ?";
    private String SQL_GET_ALL_ORDER_DETAILS = "SELECT * FROM OrderDetail WHERE orderID = ";
    @Override
    public void addOrder(Order order, List<OrderDetail> orderDetail) throws SQLException {
        conn.setAutoCommit(false);
        pstm = conn.prepareStatement(SQL_CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
        pstm.setInt(1, order.getCustomerId());
        pstm.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstm.setDouble(3, order.getTotalAmount());
        pstm.setString(4, order.getStatusString());
        pstm.executeUpdate();
        rs = pstm.getGeneratedKeys();
        int orderId = -1;
        if (rs.next()) {
            orderId = rs.getInt(1);
        }
        if (orderId != -1) {
            //insert order detail
            pstm = conn.prepareStatement(SQL_ADD_ORDER_DETAIL);
            for (OrderDetail od : orderDetail) {
                pstm.setInt(1, od.getProductId());
                pstm.setInt(2, od.getQuantity());
                pstm.setDouble(3, od.getUnitPrice());
                pstm.setDouble(4, od.getDiscount());
                pstm.setDouble(5, od.getTotalPrice());
                pstm.addBatch();
            }
            pstm.executeBatch();
        }
        conn.commit();
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
    pstm = conn.prepareStatement(SQL_UPDATE_ORDER);
    pstm.setInt(1, order.getCustomerId());
    pstm.setDouble(2, order.getTotalAmount());
    pstm.setString(3, order.getStatusString());
    pstm.setInt(4, order.getId());
    pstm.executeUpdate();
    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        conn.setAutoCommit(false);
        pstm = conn.prepareStatement(SQL_DELETE_ORDER);
        return false;
    }

    @Override
    public ArrayList<Order> getAllOrder() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        return null;
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteAllOrderByCustomerId(int customerId) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException {
        return null;
    }
}
