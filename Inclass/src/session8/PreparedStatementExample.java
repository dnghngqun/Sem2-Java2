package session8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementExample {
    public static void main(String[] args) throws SQLException {
        //get connection
        Connection connection = MySQLConnectionDB.getMyConnection();
        //created query( sql engine:  câu lệnh truy vấn sql db
        String query = "SELECT * FROM customers WHERE customer_id = ? AND first_name like ?";
        //created preparedStatement
        PreparedStatement pstm = connection.prepareStatement(query);
        //gán giá trị cho tham số theo thứ tự

        //id -> integer
        pstm.setInt(1,7);
        //first_name -> string
        pstm.setString(2,"HUNG");

        //execute query
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            System.out.println("====================");
            System.out.println("Customer id: " + rs.getInt("customer_id"));
            System.out.println("Customer name: " + rs.getString("first_name") + rs.getString("last_name"));
            System.out.println("Customer email: " + rs.getString("email"));
        }
    }
}
