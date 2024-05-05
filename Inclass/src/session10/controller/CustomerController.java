package session10.controller;

import session10.entity.Customer;
import session10.model.CustomerDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public CustomerController() throws SQLException {
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    public void addCustomer(Customer customer) throws SQLException {
        customerDAO.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.updateCustomer(customer);
    }

    public boolean removeCustomer(int id) throws SQLException {
        return customerDAO.removeCustomer(id);
    }
    public ArrayList<Customer> getAllCustomer() throws SQLException {
       return customerDAO.getAllCustomers();
    }

    public Customer getCustomerById(int id) throws SQLException {
        return customerDAO.getCustomerById(id);
    }
}
