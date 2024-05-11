package session10.controller;

import session10.entity.Customer;
import session10.entity.Entity;
import session10.entity.OrderDetail;
import session10.entity.Product;
import session10.model.CustomerDAOImpl;
import session10.model.Model;
import session10.model.OrderManagementImpl;
import session10.model.ProductDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Controller<T extends Entity<?>> {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    OrderManagementImpl orderManagement = new OrderManagementImpl();
    ProductDAOImpl productDAO = new ProductDAOImpl();

    private T type;
    public Controller() throws SQLException {
    }



   public void addEntity(T entity) throws SQLException, IllegalAccessException {
       new Model<T>().addEntity(entity);
    }


}
