//package session10.model;
//
//import session10.entity.Order;
//import session10.entity.OrderDetail;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public interface OrderManagementDAO {
//    void addOrder(Order order, List<OrderDetail> orderDetail) throws SQLException;
//    boolean updateOrder(Order order) throws SQLException;
//    boolean deleteOrder(int id) throws SQLException;
//    ArrayList<Order> getAllOrder() throws SQLException;
//    ArrayList<Order> getOrdersByCustomerId(int customerId) throws SQLException;
//    Order getOrderById(int id) throws SQLException;
//    boolean updateOrderStatus(int orderId, String status) throws SQLException;
//    boolean deleteAllOrderByCustomerId(int customerId) throws SQLException;
//
//    ArrayList<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException;
//
//    double getTotalPriceByCustomerId(int id) throws SQLException;
//
//}
