package session8;

import java.sql.*;

public class MyApplication {

    public static void main(String[] args) throws SQLException {
        getAllCustomers();
//        createCustomer(5,"NGUYEN","DIEU LINH","dieulinh@gmail.com");
        System.out.println("After create customer 5");
        getAllCustomers();

//        createCustomer(6,"Tran","Quang Linh","quanglinh@gmail.com");
        System.out.println("After create customer 6");
        getAllCustomers();

//        removeCustomer(5);
        System.out.println("After remove customer 5");
        getAllCustomers();
        getCustomerVsPreparedStatement();
    }
    //lấy toàn bộ thông tin của bảng customers
    public static void getAllCustomers() throws SQLException {
        //gọi đối tượng connection(Nhấc object Connection đặt vào class này_
        Connection connection = MySQLConnectionDB.getMyConnection();
        //tạo statement (Lệnh thực thi với csdl)
        Statement stm = connection.createStatement();
        //truy vấn csdl
        String query = "SELECT * FROM customers";
        //thực thi truy vấn và trả kết quả về cho đối tượng ResultSet
        //ResultSet: chứa kết quả trả về từ csdl
        ResultSet rs = stm.executeQuery(query);

        //Dọc bản ghi trên ResultSet
        //khi nào đọc bản ghi = null -> dừng
        while (rs.next()) {
         //đọc customers id
            int cusID = rs.getInt("customer_id");
            //có thể dùng column index
            //int cusID = rs.getInt(1);
            String cusFName = rs.getString("first_name");
            String cusLName = rs.getString("last_name");
            String cusEmail = rs.getString("email");
            System.out.println("======================");
            System.out.println(cusID + " " + cusFName + " " + cusLName + " " + cusEmail);
        }
        connection.close();
    }

    public static void createCustomer(int cusID, String cusFName, String cusLName, String cusEmail) throws SQLException {
        Connection connection = MySQLConnectionDB.getMyConnection();
        //tạo lệnh thực thi truy vấn
        String query = "INSERT INTO customers(customer_id,first_name,last_name,email) VALUES (?,?,?,?)";
        //tạo preparedStatement
        PreparedStatement pstm = connection.prepareStatement(query);
        //thiết lập tham số cho prepareStatement
        pstm.setInt(1, cusID);
        pstm.setString(2, cusFName);
        pstm.setString(3, cusLName);
        pstm.setString(4, cusEmail);
        //thực thi truy vấn
        //hàm executeUpdate() dùng cho mục đích
        pstm.executeUpdate();
        connection.close();
    }
    public static void removeCustomer(int cusID) throws SQLException {
        Connection connection = MySQLConnectionDB.getMyConnection();
        //tạo lệnh thực thi truy vấn
        //tạo statement (Lệnh thực thi với csdl)
        String query = "DELETE FROM customers WHERE customer_id = ?";
        //tạo preparedStatement
        PreparedStatement pstm = connection.prepareStatement(query);
        //thiết lập tham số cho prepareStatement
        pstm.setInt(1, cusID);
        //thực thi truy vấn
        //hàm executeUpdate() dùng cho mục đích : INSERT ,UPDATE, DELETE
        pstm.executeUpdate();
        connection.close();
    }

    public static void getCustomerVsPreparedStatement() throws SQLException {
        Connection connection = MySQLConnectionDB.getMyConnection();
        //truy vấn
        String query = "SELECT * FROM customers WHERE customer_id = ? AND last_name like ?";
        //tạo đối tượng preparedStatement
        PreparedStatement pstm = connection.prepareStatement(query);
        //thiết lập tham số cho prepareStatement
        //1 đầu tiên đại diện cho thứ tự ? đầu tiên xuất hiện trong truy vấn
        pstm.setInt(1,6);
        //thiết lập tham số cho dấu ? thứ 2
        pstm.setString(2,"DIEU LINH");

        //kết quả trả về đối tượng ResultSet
        //executeQuery() giờ ko có tham số vì đã dùng connection.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int cusID = rs.getInt("customer_id");
            String cusFName = rs.getString("first_name");
            String cusLName = rs.getString("last_name");
            String cusEmail = rs.getString("email");
            System.out.println("==========Use PreparedStatement============");
            System.out.println(cusID + " " + cusFName + " " + cusLName + " " + cusEmail);
        }
        connection.close();
    }
}
