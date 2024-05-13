//package session10.controller;
//
//import session10.entity.Product;
//import session10.model.ProductDAOImpl;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class ProductController {
//    ProductDAOImpl ProductDAO = new ProductDAOImpl();
//
//    public void addProduct(Product product) throws SQLException {
//        ProductDAO.addProduct(product);
//    }
//
//    public ArrayList<Product> getAllProducts() throws SQLException {
//        //call model step 2
//        ArrayList<Product> products = ProductDAO.getAllProducts();
//        //step 3
//        return products;
//    }
//
//    public void updateProduct(Product product, int id) throws SQLException {
//        ProductDAO.updateProduct(product, id);
//    }
//
//    public boolean removeProduct(int id) throws SQLException {
//        boolean result = ProductDAO.removeProduct(id);
//        if(result){
//            System.out.println("Product with ID " + id + " has been successfully removed.");
//        }else {
//            System.out.println("Failed to remove Product with ID " + id);
//        }
//        return result;
//    }
//
//    public ArrayList<Product> getProductsByName(String name) throws SQLException {
//        return ProductDAO.getProductsByName(name);
//    }
//
//    public Product getProductByID(int id) throws SQLException {
//        return ProductDAO.getProductByID(id);
//    }
//
//}
