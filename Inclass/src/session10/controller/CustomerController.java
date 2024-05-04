package session10.controller;

import session10.model.CustomerDAO;

import java.sql.SQLException;

public class CustomerController {
    CustomerDAO CustomerDAO = new CustomerDAO();

    public CustomerController() throws SQLException {
    }

    public void addCustomer(int id, String name, String email, String address) throws SQLException {
        CustomerDAO.addCustomer(id, name, email, address);
    }

    public void updateCustomer(int id, String name, String email, String address) throws SQLException {
        CustomerDAO.updateCustomer(id, name, email, address);
    }

    public void removeCustomer(int id) throws SQLException {
        CustomerDAO.removeCustomer(id);
    }
}
