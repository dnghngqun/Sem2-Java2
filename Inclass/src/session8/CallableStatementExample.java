package session8;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementExample {
    public static void main(String[] args) throws SQLException {
        Connection connection = MySQLConnectionDB.getMyConnection();
        //"Ẩn" đi các SQL engine
        String sql = "{CALL getCustomer(?,?,?,?)}";
        CallableStatement cstm = connection.prepareCall(sql);

        cstm.setInt(1, 8);
        cstm.registerOutParameter(2, Types.VARCHAR);
        cstm.registerOutParameter(3, Types.VARCHAR);
        cstm.registerOutParameter(4, Types.VARCHAR);

        cstm.executeUpdate();
//        System.out.println("Customer id: " + cstm.getInt(1));
        System.out.println("First name: " + cstm.getString(2));
        System.out.println("Last name: " + cstm.getString(3));
        System.out.println("Email: " + cstm.getString(4));
    }
}
