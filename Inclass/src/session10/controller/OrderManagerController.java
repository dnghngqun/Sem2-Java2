//package session10.controller;
//
//import session10.entity.Order;
//import session10.entity.OrderDetail;
//import session10.model.OrderManagementImpl;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderManagerController {
//    OrderManagementImpl orderManagement = new OrderManagementImpl();
//    public void addOrder(Order order, List<OrderDetail> orderDetail) throws SQLException {
//        order.setStatus(1);//default status is Pending
//        for(OrderDetail od: orderDetail){
//            od.setTotalPrice(od.getUnitPrice() * od.getQuantity() - od.getUnitPrice() * od.getDiscount());
//        }
//        orderManagement.addOrder(order, orderDetail);
//    }
//
//    public boolean updateOrder(Order order) throws SQLException {
//        return orderManagement.updateOrder(order);
//    }
//
//    public boolean deleteOrder(int id) throws SQLException {
//        boolean result = orderManagement.deleteOrder(id);
//        if(result){
//            System.out.println("Order with ID " + id + " has been successfully removed.");
//            return true;
//        }else {
//            System.out.println("Failed to remove order with ID " + id + ".");
//            return false;
//        }
//    }
//
//    public List<Order> getAllOrders() throws SQLException {
//        return orderManagement.getAllOrder();
//    }
//
//    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
//        return orderManagement.getOrdersByCustomerId(customerId);
//    }
//
//    public Order getOrderById(int id) throws SQLException {
//        return orderManagement.getOrderById(id);
//    }
//
//    public boolean updateOrderStatus(int orderId, int status) throws SQLException {
//        Order order = new Order();
////        order.setStatus(status);
//
//        boolean result = orderManagement.updateOrderStatus(orderId, order.getStatus());
//        if(result) {
//            System.out.println("Order with ID " + orderId + " has been successfully updated.");
//            return true;
//        }else {
//            System.out.println("Failed to update order with ID " + orderId + ".");
//            System.out.println("Please try again.");
//            return false;
//        }
//
//    }
//    public ArrayList<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException {
//        return orderManagement.showOrderDetailsByCustomerID(id);
//    }
//
//    public double getTotalPriceByCustomerId(int id) throws SQLException {
//        return orderManagement.getTotalPriceByCustomerId(id);
//    }
//}
