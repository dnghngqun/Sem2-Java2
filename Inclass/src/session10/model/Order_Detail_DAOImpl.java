package session10.model;

import session10.entity.OrderDetail;

import java.sql.*;
import java.util.ArrayList;

public class Order_Detail_DAOImpl implements Order_DetailDAO{
    private static final Connection conn;

    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement psvm = null;

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
    private String SQL_ADD_ORDER_DETAIL = "INSERT INTO OrderDetail(id, orderID, productID, quantity, unitPrice, discount, totalPrice) VALUES (?,?,?,?,?,?,?)";
    private String SQL_UPDATE_ORDER_DETAIL = "UPDATE OrderDetail SET orderID = ?, productID = ?, quantity = ?, unitPrice = ?, discount = ?, totalPrice = ? WHERE id = ?";
    private String SQL_DELETE_ORDER_DETAIL = "DELETE FROM OrderDetail WHERE id = ?";
    @Override
    public ArrayList<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        psvm = conn.prepareStatement(SQL_GET_ORDER_DETAIL_BY_ORDER_ID);
        psvm.setInt(1, id);
        ResultSet rs = psvm.executeQuery();
        while(rs.next()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(rs.getInt("Order_Detail_ID"));
            orderDetail.setCustomerName(rs.getString("CustomerName"));
            orderDetail.setProductName(rs.getString("ProductName"));
            orderDetail.setQuantity(rs.getInt("quantity"));
            orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
            orderDetail.setDiscount(rs.getDouble("discount"));
            orderDetail.setTotalPrice(rs.getDouble("totalPrice"));
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) throws SQLException {
        psvm = conn.prepareStatement(SQL_ADD_ORDER_DETAIL);
        psvm.setInt(1, orderDetail.getId());
        psvm.setInt(2, orderDetail.getOrderId());
        psvm.setInt(3, orderDetail.getProductId());
        psvm.setInt(4, orderDetail.getQuantity());
        psvm.setDouble(5, orderDetail.getUnitPrice());
        psvm.setDouble(6, orderDetail.getDiscount());
        psvm.setDouble(7, orderDetail.getTotalPrice());
        psvm.executeUpdate();
    }

    @Override
    public boolean deleteOrderDetail(int id) throws SQLException {
        psvm = conn.prepareStatement(SQL_DELETE_ORDER_DETAIL);
        psvm.setInt(1, id);

        int rs = psvm.executeUpdate();
        if(rs > 0) return true;
        return false;
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) throws SQLException {
        psvm = conn.prepareStatement(SQL_UPDATE_ORDER_DETAIL);
        psvm.setInt(1, orderDetail.getOrderId());
        psvm.setInt(2, orderDetail.getProductId());
        psvm.setInt(3, orderDetail.getQuantity());
        psvm.setDouble(4, orderDetail.getUnitPrice());
        psvm.setDouble(5, orderDetail.getDiscount());
        psvm.setDouble(6, orderDetail.getTotalPrice());
        psvm.setInt(7, orderDetail.getId());
        psvm.executeUpdate();
    }
}
