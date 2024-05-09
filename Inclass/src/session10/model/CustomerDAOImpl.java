package session10.model;


import session10.entity.Customer;
import session10.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDAOImpl implements CustomerDAO {
    private static final Connection conn;
    private String SQL_CREATE_CUSTOMER = "INSERT INTO customers(name, address, email) VALUES (?,?,?)";
    private String SQL_UPDATE_CUSTOMER = "UPDATE customers SET name = ?, address = ?, email = ? WHERE id = ?";
    private String SQL_DELETE_CUSTOMER = "DELETE FROM customers WHERE id = ?";
    private String SQL_GET_ALL_CUSTOMERS = "SELECT * FROM customers";
    private String SQL_GET_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";
    private String SQL_GET_CUSTOMER_BY_NAME = "SELECT * FROM customers WHERE name = ?";
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
    public void addCustomer(Customer customer) throws SQLException {
        pstm = conn.prepareStatement(SQL_CREATE_CUSTOMER, PreparedStatement.RETURN_GENERATED_KEYS);
        pstm.setString(1, customer.getName());
        pstm.setString(2, customer.getAddress());
        pstm.setString(3, customer.getEmail());
        pstm.executeUpdate();
        System.out.println("Insert Success!");
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        ArrayList<Customer> list = new ArrayList<>();
        pstm = conn.prepareStatement(SQL_GET_ALL_CUSTOMERS);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setEmail(rs.getString("email"));
            list.add(customer);
        }
        return list;
    }

    @Override
    public boolean removeCustomer(int id) throws SQLException {
        Scanner input = new Scanner(System.in);
        ArrayList<Order> orders = new OrderDAOImpl().getOrdersByCustomerId(id);
        if(orders.size() > 0) {
            System.out.println("To delete Customer ID: "+ id +", please delete all Orders first!");
            System.out.print("Do you want to delete all Orders of Customer?(Y/N): ");
            String Stringchoice = input.nextLine();
            char choice = Stringchoice.charAt(0);
            if(choice == 'Y' || choice == 'y') {
                boolean result1 = new OrderDAOImpl().deleteAllOrderByCustomerId(id);
                if(result1) {
                    input.close();
                    System.out.println("All Orders of Customer has been successfully removed!");
                }else System.out.println("Something wrong!");
            }else if(choice == 'N' || choice == 'n') {
                System.exit(0);
            }
            else {
                input.close();
                System.out.println("Invalid input!!!");
            }
        }
        pstm = conn.prepareStatement(SQL_DELETE_CUSTOMER);
        pstm.setInt(1, id);
        int rowAffected = pstm.executeUpdate();
        if(rowAffected > 0) {
            System.out.println("Delete Success!");
            return true;
        }
        return false;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        pstm = conn.prepareStatement(SQL_UPDATE_CUSTOMER);
        pstm.setString(1, customer.getName());
        pstm.setString(2, customer.getAddress());
        pstm.setString(3, customer.getEmail());
        pstm.setInt(4, customer.getId());
        pstm.executeUpdate();
        System.out.println("Update Success!");
    }

    @Override
    public ArrayList<Customer> getCustomersByName(String name) throws SQLException {
        ArrayList<Customer> list = new ArrayList<>();
        pstm = conn.prepareStatement(SQL_GET_CUSTOMER_BY_NAME);
        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setEmail(rs.getString("email"));
            list.add(customer);
        }
        return list;
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        pstm = conn.prepareStatement(SQL_GET_CUSTOMER_BY_ID);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setEmail(rs.getString("email"));
            return customer;
        }
        return null;
    }
}
