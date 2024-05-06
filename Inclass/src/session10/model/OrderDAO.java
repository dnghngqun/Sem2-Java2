package session10.model;

import session10.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO {
    void addOrder(Order order) throws SQLException;
    void updateOrder(Order order) throws SQLException;
    boolean deleteOrder(int id) throws SQLException;
    Order getOrderById(int id) throws SQLException;
    ArrayList<Order> getOrdersByCustomerId(int customerId) throws SQLException;
    ArrayList<Order> getAllOrders() throws SQLException;
    boolean updateOrderStatus(int orderId, String status) throws SQLException;
}
