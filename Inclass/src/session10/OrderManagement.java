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
        //tắt chế độ tự động commit trên kết nối cơ sở dữ liệu.
        // Khi bạn tắt chế độ tự động commit, các thay đổi trong cơ sở dữ liệu sẽ không được tự động lưu lại ngay
        // sau mỗi lệnh SQL được thực thi.
        // Thay vào đó, bạn cần phải gọi conn.commit() để lưu lại các thay đổi hoặc conn.rollback() để hủy bỏ các thay đổi.
        String orderQuery = "INSERT INTO orders(customer_id, create_at, update_at, payment_type) VALUES(?,?,?,?)";
        String orderDetailQuery = "INSERT INTO order_details(order_id, create_at, update_at,product_id, price) VALUES(?,?,?,?,?)";
        pstm = conn.prepareStatement(orderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        // PreparedStatement.RETURN_GENERATED_KEYS: trả về các khóa sinh tự động được tạo ra khi thực hiện truy vấn.
        // Thường được sử dụng trong các trường hợp khi bạn thêm dữ liệu vào một bảng có trường tự tăng (auto-increment),
        // và bạn muốn lấy giá trị của trường tự tăng đó sau khi thêm dữ liệu.
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
