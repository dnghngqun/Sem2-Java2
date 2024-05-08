package session10.controller;

import session10.entity.OrderDetail;
import session10.model.Order_Detail_DAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailController {
    Order_Detail_DAOImpl OrderDetailDAO = new Order_Detail_DAOImpl();

    public void addOrderDetail(OrderDetail orderDetail) throws SQLException {
        OrderDetailDAO.addOrderDetail(orderDetail);
    }

    public boolean deleteOrderDetail(int id) throws SQLException {
        return OrderDetailDAO.deleteOrderDetail(id);
    }

    public void updateOrderDetail(OrderDetail orderDetail) throws SQLException {
        OrderDetailDAO.updateOrderDetail(orderDetail);
    }

    public ArrayList<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException {
        return OrderDetailDAO.showOrderDetailsByCustomerID(id);
    }
}
