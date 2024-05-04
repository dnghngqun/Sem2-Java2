package session10.controller;

import session10.entity.Product;
import session10.model.ProductDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {
    ProductDAOImpl ProductDAO = new ProductDAOImpl();

    public void addProduct(Product product) throws SQLException {
        ProductDAO.addProduct(product);
    }

    public ArrayList<Product> getAllProducts() throws SQLException {
        //call model step 2
        ArrayList<Product> products = ProductDAO.getAllProducts();
        //step 3
        return products;
    }

}
