package session10.model;

import java.sql.SQLException;

public interface Customer {
    void addCustomer(int id, String name, String email, String address) throws SQLException;
    void removeCustomer(int id) throws SQLException;
    void updateCustomer(int id, String name, String email, String address) throws SQLException;
}
