package session9.controller;

import session9.entity.Customers;
import session9.model.CustomerDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    CustomerDAOImpl CustomerDAO = new CustomerDAOImpl();

    public CustomerController() throws SQLException {
    }


    public ArrayList<Customers> getAllCustomers() throws SQLException {
        ArrayList<Customers> customers = CustomerDAO.getAllCustomers();
        return customers;
    }

   public Customers findCustomerById(int id) throws SQLException {
        Customers customers = CustomerDAO.findCustomerById(id);
        return customers;
    }
}

