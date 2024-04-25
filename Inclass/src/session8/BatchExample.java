package session8;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchExample {
    public static void main(String[] args) throws SQLException {

        Connection connection = MySQLConnectionDB.getMyConnection();
        //xử lý theo batch, cùng 1 lúc chạy nhiều câu lệnh
        //update, insert
        Statement stm = connection.createStatement();
        //query1
        String query1 = "update cusstomers set first_name = 'Tran' where customer_id = 1";
        stm.addBatch(query1);

        //query2
        String query2 = "update customers set first_name = 'Ngo' where customer_id = 2";
        stm.addBatch(query2);

        int count[] = stm.executeBatch();

        System.out.println("Query1: " + count[0]);
        System.out.println("Query2: " + count[1]);
        connection.commit();
    }
}
