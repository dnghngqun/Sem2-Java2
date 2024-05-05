package session10.model;

import session10.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    void addCustomer(Customer customer) throws SQLException;

    ArrayList<Customer> getAllCustomers() throws SQLException;

    boolean removeCustomer(int id) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    ArrayList<Customer> getCustomersByName(String name) throws SQLException;

    Customer getCustomerById(int id) throws SQLException;
}
