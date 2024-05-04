package session9.model;

import session9.entity.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDAOImpl implements CustomerDAO{
    private static final Connection conn;
    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement pstm = null;

    public CustomerDAOImpl() throws SQLException {
    }

    @Override
    public ArrayList<Customers> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM customers";
        pstm = conn.prepareStatement(query);

        ResultSet rs = pstm.executeQuery();

        ArrayList<Customers> customers = new ArrayList<>();

        while(rs.next()) {
            int cusID = rs.getInt("customer_id");
            String cusFName = rs.getString("first_name");
            String cusLName = rs.getString("last_name");
            String cusEmail = rs.getString("email");
            customers.add(new Customers(cusID, cusFName, cusLName, cusEmail));
        }

        return customers;
    }


    @Override
    public  Customers findCustomerById(int id) throws SQLException {
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        pstm = conn.prepareStatement(query);

        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if(rs.next()) {
            int cusID = rs.getInt("customer_id");
            String cusFName = rs.getString("first_name");
            String cusLName = rs.getString("last_name");
            String cusEmail = rs.getString("email");
            return new Customers(cusID, cusFName, cusLName, cusEmail);
        }

        return null;
    }

    @Override
    public void removeCustomer(int id) throws SQLException {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        pstm = conn.prepareStatement(query);
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }
    @Override
    public void addCustomer(int id, String fName, String lName, String email) throws SQLException {
        String query = "INSERT INTO customers(customer_id, first_name,last_name,email) VALUES (?,?,?,?)";
        pstm = conn.prepareStatement(query);
        pstm.setInt(1, id);
        pstm.setString(2, fName);
        pstm.setString(3, lName);
        pstm.setString(4, email);
        pstm.executeUpdate();
    }
}
