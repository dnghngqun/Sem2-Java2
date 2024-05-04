package session10.entity;

public class Product {
    private int ProductID;
    private String ProductName;
    private double Price;
    private String description;

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getPrice() {
        return Price;
    }

    public String getDescription() {
        return description;
    }
}