//package session10.controller;
//
//import session10.entity.Customer;
//import session10.model.CustomerDAOImpl;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomerController {
//    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
//
//    public CustomerController() throws SQLException {
//    }
//
//    public List<Customer> getAllCustomers() throws SQLException {
//        return customerDAO.getAllCustomers();
//    }
//
//    public void addCustomer(Customer customer) throws SQLException {
//        customerDAO.addCustomer(customer);
//    }
//
//    public void updateCustomer(int id,Customer customer) throws SQLException {
//        customerDAO.updateCustomer(id, customer);
//    }
//
//    public boolean removeCustomer(int id) throws SQLException {
//        boolean result = customerDAO.removeCustomer(id);
//        if(result) {
//            System.out.println("Customer with ID " + id + " has been successfully removed.");
//        }else {
//            System.out.println("Failed to remove customer with ID " + id + ".");
//        }
//        return result;
//    }
//
//    public List<Customer> getAllCustomer() throws SQLException {
//       return customerDAO.getAllCustomers();
//    }
//
//    public Customer getCustomerById(int id) throws SQLException {
//        return customerDAO.getCustomerById(id);
//    }
//
//    public List<Customer> getCustomersByName(String name) throws SQLException {
//        return customerDAO.getCustomersByName(name);
//    }
//}
