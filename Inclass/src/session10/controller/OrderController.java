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
        orderDAO.addOrder(order);
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
        boolean result = true;

           // 0: Cancelled, 1: Pending, 2: Completed
           String statusString = null;
           switch (status) {
               case 0:
                   statusString = "Cancelled";
                   break;
               case 1:
                   statusString = "Pending";
                   break;
               case 2:
                   statusString = "Completed";
                   break;
               default:
                   result = false;
                   System.out.println("Invalid status. Please try again.");
                   break;
           }

        if(result){
            boolean result2 = orderDAO.updateOrderStatus(orderId, statusString);
            if(result2) {
                System.out.println("Order with ID " + orderId + " has been successfully updated.");
            }else {
                System.out.println("Failed to update order with ID " + orderId + ".");
                System.out.println("Please try again.");
                result = false;
            }
        }
        return result;
    }
}
