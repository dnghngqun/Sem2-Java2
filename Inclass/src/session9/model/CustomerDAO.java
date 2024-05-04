package session9.model;

import session9.entity.Customers;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<Customers> getAllCustomers() throws SQLException;

    Customers findCustomerById(int id) throws SQLException;

    void addCustomer(int id, String fname, String lname, String email) throws SQLException;
    void removeCustomer(int id) throws SQLException;
}