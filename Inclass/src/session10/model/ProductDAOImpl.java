package session10.model;

import session10.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    private static final Connection conn;
    private final String SQL_CREATE_PRODUCT = "INSERT INTO Product(ProductID, ProductName, Price, Description) VALUES (?,?,?,?)";
    private final String SQL_GET_BY_ID = "SELECT * FROM Product WHERE ProductID = ?";
    private final String SQL_UPDATE_PRODUCT = "UPDATE Product SET ProductName = ?, Price = ?, Description = ? WHERE ProductID = ?";
    private final String SQL_REMOVE_PRODUCT = "DELETE FROM Product WHERE ProductID = ?";
    private final String SQL_GET_BY_NAME = "SELECT * FROM Product WHERE ProductName = ?";
    private final String SQL_GET_ALL = "SELECT * FROM Product";

    static {
        try {
            conn = MySQLConnectionDB.getMyConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement pstm = null;


    public void addProduct(Product product) throws SQLException {
    pstm = conn.prepareStatement(SQL_CREATE_PRODUCT);
    pstm.setInt(1, product.getProductID());
    pstm.setString(2, product.getProductName());
    pstm.setDouble(3, product.getPrice());
    pstm.setString(4, product.getDescription());
    pstm.executeUpdate();
    System.out.println("Insert Success");
    }

    @Override
    public ArrayList<Product> getAllProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        pstm = conn.prepareStatement(SQL_GET_ALL);

        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setPrice(rs.getDouble("Price"));
            product.setDescription(rs.getString("Description"));
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductByID(int id) throws SQLException {
        pstm = conn.prepareStatement(SQL_GET_BY_ID);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setPrice(rs.getDouble("Price"));
            product.setDescription(rs.getString("Description"));
            return product;
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        pstm = conn.prepareStatement(SQL_UPDATE_PRODUCT);
        pstm.setString(1, product.getProductName());
        pstm.setDouble(2, product.getPrice());
        pstm.setString(3, product.getDescription());
        pstm.setInt(4, product.getProductID());
        pstm.executeUpdate();
    }

    @Override
    public Boolean removeProduct(int id) throws SQLException {
        pstm = conn.prepareStatement(SQL_REMOVE_PRODUCT);
        pstm.setInt(1, id);
        int rs = pstm.executeUpdate();
        if (rs > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Product> getProductsByName(String name) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        pstm = conn.prepareStatement(SQL_GET_BY_NAME);
        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("ProductID"));
            product.setProductName(rs.getString("ProductName"));
            product.setPrice(rs.getDouble("Price"));
            product.setDescription(rs.getString("Description"));
            products.add(product);
        }
        return products;
    }
}
