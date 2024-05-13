//package session10.model;
//
//import session10.entity.Order;
//import session10.entity.OrderDetail;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class OrderManagementImpl implements OrderManagementDAO{
//    private static final Connection conn;
//
//    static {
//        try {
//            conn = MySQLConnectionDB.getMyConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    PreparedStatement pstm = null;
//    ResultSet rs = null;
//    private String SQL_CREATE_ORDER = "INSERT INTO Order_table(customerID, orderDate, totalAmount, status) VALUES (?,?,?,?)";
//    private String SQL_UPDATE_ORDER = "UPDATE Order_table SET customerID = ?, totalAmount = ?, status = ? WHERE id = ?";
//    private String SQL_DELETE_ORDER = "DELETE FROM Order_table WHERE id = ?";
//    private String SQL_GET_ALL_ORDERS = "SELECT o.id AS OrderID, customerID, c.name AS CustomerName," +
//            "o.orderDate," +
//            "o.totalAmount," +
//            "o.status " +
//            "FROM Order_table o " +
//            "INNER JOIN customers c on o.customerID = c.id";
//    private String SQL_GET_ORDER_BY_ID = "SELECT o.id AS OrderID," +
//            "customerID," +
//            "c.name AS CustomerName," +
//            "o.orderDate," +
//            "o.totalAmount," +
//            "o.status " +
//            "FROM Order_table o " +
//            "INNER JOIN customers c on o.customerID = c.id " +
//            "WHERE o.id = ?";
//    private String SQL_GET_ORDERS_BY_CUSTOMER_ID = "SELECT o.id AS OrderID," +
//            "customerID," +
//            "c.name AS CustomerName," +
//            "o.orderDate," +
//            "o.totalAmount," +
//            "o.status " +
//            "FROM Order_table o " +
//            "INNER JOIN customers c on o.customerID = c.id " +
//            "WHERE o.customerID = ?";
//    private String SQL_UPDATE_STATUS = "UPDATE Order_table SET status = ? WHERE id = ?";
//    private String SQL_DELETE_ORDER_BY_CUSTOMER_ID = "DELETE FROM Order_table WHERE customerID = ?";
//
//    private String SQL_ADD_ORDER_DETAIL = "INSERT INTO OrderDetail(orderID, productID, quantity, unitPrice, discount, totalPrice) VALUES (?,?,?,?,?,?)";
//    private String SQL_UPDATE_ORDER_DETAIL = "UPDATE OrderDetail SET orderID = ?, productID = ?, quantity = ?, unitPrice = ?, discount = ?, totalPrice = ? WHERE id = ?";
//    private String SQL_DELETE_ORDER_DETAIL = "DELETE FROM OrderDetail WHERE id = ?";
//    private String SQL_GET_ALL_ORDER_DETAILS = "SELECT * FROM OrderDetail WHERE orderID = ";
//    private String SQL_GET_ORDER_DETAIL_BY_CUSTOMER_ID = "SELECT od.id AS Order_Detail_ID, " +
//            "c.name AS CustomerName, " +
//            "p.ProductName, " +
//            "od.quantity, " +
//            "od.unitPrice, " +
//            "od.discount, " +
//            "od.totalPrice " +
//            "FROM OrderDetail od " +
//            "INNER JOIN Order_table o on od.orderID = o.id " +
//            "INNER JOIN customers c on o.customerID = c.id " +
//            "INNER JOIN Product p on od.productID = p.ProductID " +
//            "WHERE customerID = ?";
//    private String SQL_DELETE_ALL_ORDER_DETAILS_BY_CUSTOMER_ID = "DELETE FROM OrderDetail WHERE customerID = ?";
//    private String SQL_GET_ALL_TOTAL_AMOUNT_BY_CUSTOMER_ID = "SELECT totalPrice FROM OrderDetail od " +
//            "INNER JOIN Order_table Ot on od.orderID = Ot.id " +
//            "WHERE customerID = ?";
//    @Override
//    public void addOrder(Order order, List<OrderDetail> orderDetail) throws SQLException {
//        conn.setAutoCommit(false);
//        pstm = conn.prepareStatement(SQL_CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
//        pstm.setInt(1, order.getCustomerId());
//        pstm.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
//        pstm.setDouble(3, order.getTotalAmount());
//        pstm.setString(4, order.getStatus());
//        pstm.executeUpdate();
//        rs = pstm.getGeneratedKeys();
//        int orderId = -1;
//        if (rs.next()) {
//            orderId = rs.getInt(1);
//        }
//        if (orderId != -1) {
//            //insert order detail
//            pstm = conn.prepareStatement(SQL_ADD_ORDER_DETAIL);
//            for (OrderDetail od : orderDetail) {
//
//                pstm.setInt(1, orderId);
//                pstm.setInt(2, od.getProductId());
//                pstm.setInt(3, od.getQuantity());
//                pstm.setDouble(4, od.getUnitPrice());
//                pstm.setDouble(5, od.getDiscount());
//                pstm.setDouble(6, od.getTotalPrice());
//                pstm.addBatch();
//            }
//            pstm.executeBatch();
//        }
//        conn.commit();
//    }
//
//    @Override
//    public boolean updateOrder(Order order) throws SQLException {
//    pstm = conn.prepareStatement(SQL_UPDATE_ORDER);
//    pstm.setInt(1, order.getCustomerId());
//    pstm.setDouble(2, order.getTotalAmount());
//    pstm.setString(3, order.getStatus());
//    pstm.setInt(4, order.getId());
//    int rs = pstm.executeUpdate();
//    if(rs > 0){
//        return true;
//    }
//        return false;
//    }
//
//    @Override
//    public boolean deleteOrder(int id) throws SQLException {
//        conn.setAutoCommit(false);
//        pstm = conn.prepareStatement(SQL_DELETE_ORDER_DETAIL);
//        pstm.setInt(1, id);
//        int result = pstm.executeUpdate();
//        if(result > 0){
//            pstm = conn.prepareStatement(SQL_DELETE_ORDER);
//            pstm.setInt(1, id);
//            int rs = pstm.executeUpdate();
//            if (rs > 0) {
//                conn.commit();
//                return true;
//            }else {
//                conn.rollback();
//                return false;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public ArrayList<Order> getAllOrder() throws SQLException {
//        pstm = conn.prepareStatement(SQL_GET_ALL_ORDERS);
//        ResultSet rs = pstm.executeQuery();
//        ArrayList<Order> list = new ArrayList<>();
////        while (rs.next()) {
////            Order order = new Order();
////            order.setId(rs.getInt("OrderID"));
////            order.setCustomerId(rs.getInt("customerID"));
////            order.setCustomerName(rs.getString("CustomerName"));
////            order.setOrderDate(rs.getDate("orderDate"));
////            order.setTotalAmount(rs.getDouble("totalAmount"));
////            order.setStatus(rs.getString("status"));
////            list.add(order);
////        }
//        return list;
//    }
//
//    @Override
//    public ArrayList<Order> getOrdersByCustomerId(int customerId) throws SQLException {
//        pstm = conn.prepareStatement(SQL_GET_ORDERS_BY_CUSTOMER_ID);
//        pstm.setInt(1, customerId);
//        ResultSet rs = pstm.executeQuery();
//        ArrayList<Order> list = new ArrayList<>();
//        while (rs.next()) {
//            Order order = new Order();
//            order.setId(rs.getInt("OrderID"));
//            order.setCustomerId(rs.getInt("customerID"));
//            order.setOrderDate(rs.getDate("orderDate"));
//            order.setTotalAmount(rs.getDouble("totalAmount"));
//            order.setStatus(rs.getString("status"));
//            list.add(order);
//        }
//        return list;
//    }
//
//    @Override
//    public Order getOrderById(int id) throws SQLException {
//        pstm = conn.prepareStatement(SQL_GET_ORDER_BY_ID);
//        pstm.setInt(1, id);
//        ResultSet rs = pstm.executeQuery();
//        while (rs.next()) {
//            Order order = new Order();
//            order.setId(rs.getInt("OrderID"));
//            order.setCustomerId(rs.getInt("customerID"));
//            order.setOrderDate(rs.getDate("orderDate"));
//            order.setTotalAmount(rs.getDouble("totalAmount"));
//            order.setStatus(rs.getString("status"));
//            return order;
//        }
//        return null;
//    }
//
//    @Override
//    public boolean updateOrderStatus(int orderId, String status) throws SQLException {
//        pstm = conn.prepareStatement(SQL_UPDATE_STATUS);
//        pstm.setString(1, status);
//        pstm.setInt(2, orderId);
//        int rowAffected = pstm.executeUpdate();
//        if(rowAffected > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteAllOrderByCustomerId(int customerId) throws SQLException {
//        conn.setAutoCommit(false);
//        pstm = conn.prepareStatement(SQL_DELETE_ALL_ORDER_DETAILS_BY_CUSTOMER_ID);
//        pstm.setInt(1, customerId);
//        System.out.println("This action will delete all order details of customers with id: "+ customerId);
//        System.out.print("Do you want to continue? (Y/N): ");
//        String choice = new Scanner(System.in).nextLine();
//        char getchoice = choice.charAt(0);
//        if (getchoice != 'Y' && getchoice != 'y') {
//            conn.rollback();
//            System.out.println("Delete cancelled.");
//            return false;
//        }
//        int result = pstm.executeUpdate();
//        if(result > 0){
//            pstm = conn.prepareStatement(SQL_DELETE_ORDER_BY_CUSTOMER_ID);
//            pstm.setInt(1, customerId);
//            int rs = pstm.executeUpdate();
//            if (rs > 0) {
//                conn.commit();
//                return true;
//            }else {
//                conn.rollback();
//                return false;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public ArrayList<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException {
//        pstm = conn.prepareStatement(SQL_GET_ORDER_DETAIL_BY_CUSTOMER_ID);
//        pstm.setInt(1, id);
//        ResultSet rs = pstm.executeQuery();
//        ArrayList<OrderDetail> list = new ArrayList<>();
//        while (rs.next()) {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setId(rs.getInt("Order_Detail_ID"));
//            orderDetail.setCustomerName(rs.getString("CustomerName"));
//            orderDetail.setProductName(rs.getString("ProductName"));
//            orderDetail.setQuantity(rs.getInt("quantity"));
//            orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
//            orderDetail.setDiscount(rs.getDouble("discount"));
//            orderDetail.setTotalPrice(rs.getDouble("totalPrice"));
//            list.add(orderDetail);
//        }
//        return list;
//    }
//
//    @Override
//    public double getTotalPriceByCustomerId(int id) throws SQLException {
//        pstm = conn.prepareStatement(SQL_GET_ALL_TOTAL_AMOUNT_BY_CUSTOMER_ID);
//        pstm.setInt(1, id);
//        rs = pstm.executeQuery();
//        double total = 0;
//        while (rs.next()) {
//            total += rs.getDouble("totalPrice");
//        }
//        return total;
//    }
//}
