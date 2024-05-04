package session10.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO implements Customer {
    private static final Connection conn;

    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement pstm = null;

    public CustomerDAO() throws SQLException {
    }

    @Override
    public void addCustomer(int id, String name, String email, String address) throws SQLException {
        String query = "INSERT INTO customers(id, name, email, address) VALUES (?,?,?,?)";
        pstm = conn.prepareStatement(query);
        pstm.setInt(1, id);
        pstm.setString(2, name);
        pstm.setString(3, email);
        pstm.setString(4, address);
        pstm.executeUpdate();
    }

    @Override
    public void removeCustomer(int id) throws SQLException {
        String query = "DELETE FROM customers WHERE id = ?";
        pstm = conn.prepareStatement(query);
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }

    @Override
    public void updateCustomer(int id, String name, String email, String address) throws SQLException {
        String query = "UPDATE customers SET name = ?, email = ?, address = ? WHERE id = ?";
        pstm = conn.prepareStatement(query);
        pstm.setString(1, name);
        pstm.setString(2, email);
        pstm.setString(3, address);
        pstm.setInt(4, id);
        pstm.executeUpdate();
    }
}
