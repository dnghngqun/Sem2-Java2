package session10.controller;

import session10.entity.Order;
import session10.model.OrderDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderController {
    OrderDAOImpl orderDAO = new OrderDAOImpl();

    public void addOrder(Order order) throws SQLException {
        Order order1 = new Order();
        order1.setId(order.getId());
        order1.setCustomerId(order.getCustomerId());
        order1.setOrderDate(new Date());
        order1.setStatus(1);//default: Pending
        orderDAO.addOrder(order1);
    }

    public void updateOrder(Order order) throws SQLException {
        orderDAO.updateOrder(order);
    }

    public boolean deleteOrder(int id) throws SQLException {
       boolean result = orderDAO.deleteOrder(id);
       if(result){
           System.out.println("Order with ID " + id + " has been successfully removed.");
           return true;
       }else {
           System.out.println("Failed to remove order with ID " + id + ".");
           return false;
       }
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    public ArrayList<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        return orderDAO.getOrdersByCustomerId(customerId);
    }

    public Order getOrderById(int id) throws SQLException {
        return orderDAO.getOrderById(id);
    }

    public boolean updateOrderStatus(int orderId, int status) throws SQLException {
        Order order = new Order();
        order.setStatus(status);

        boolean result = orderDAO.updateOrderStatus(orderId, order.getStatusString());
        if(result) {
            System.out.println("Order with ID " + orderId + " has been successfully updated.");
            return true;
        }else {
            System.out.println("Failed to update order with ID " + orderId + ".");
            System.out.println("Please try again.");
            return false;
            }

    }
}
