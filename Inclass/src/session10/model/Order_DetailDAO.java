package session10.model;

import session10.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Order_DetailDAO {
    ArrayList<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException;
    void addOrderDetail(OrderDetail orderDetail) throws SQLException;

    boolean deleteOrderDetail(int id) throws SQLException;
    void updateOrderDetail(OrderDetail orderDetail) throws SQLException;
}
