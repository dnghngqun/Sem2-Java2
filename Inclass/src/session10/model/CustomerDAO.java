package session10.model;

import session10.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer) throws SQLException;

    List<Customer> getAllCustomers() throws SQLException;

    boolean removeCustomer(int id) throws SQLException;

    void updateCustomer(int id, Customer customer) throws SQLException;

    List<Customer> getCustomersByName(String name) throws SQLException;

    Customer getCustomerById(int id) throws SQLException;
}
