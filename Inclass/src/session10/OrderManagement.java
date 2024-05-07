package session10;

import session10.entity.OrderDetail;
import session10.model.MySQLConnectionDB;

import java.sql.*;
import java.util.List;

public class OrderManagement {
    public void createOrder(int customer_id, String payment_type, List<OrderDetail> orderDetails) throws SQLException {
        Connection conn = MySQLConnectionDB.getMyConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        conn.setAutoCommit(false);//đánh dấu điểm bắt đầu của transaction
        String orderQuery = "INSERT INTO orders(customer_id, create_at, update_at, payment_type) VALUES(?,?,?,?)";
        String orderDetailQuery = "INSERT INTO order_details(order_id, create_at, update_at,product_id, price) VALUES(?,?,?,?,?)";
        pstm = conn.prepareStatement(orderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        pstm.setInt(1, 1);
        pstm.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstm.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstm.setString(4, "Credit Card");
        pstm.executeUpdate();
        rs = pstm.getGeneratedKeys();
        int orderId = -1;
        if(rs.next()) {
            orderId = rs.getInt(1);
        }
        if(orderId!=-1) {
            //insert order detail
            pstm = conn.prepareStatement(orderDetailQuery);
            for(OrderDetail orderDetail : orderDetails) {
                pstm.setInt(1, orderId);
                pstm.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
                pstm.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
                pstm.setInt(4, 1);
                pstm.setDouble(5, 10000);
                pstm.addBatch();//cứ có 1 chi tiết đon hàng thì dc thêm vào 1 lô(batch)
            }
            pstm.executeBatch();//thực thi cả batch(Insert nhiều bản ghi cùng lúc)
        }
        conn.commit(); //đánhh dấu giao dịch (transaction) thành công
    }
}
