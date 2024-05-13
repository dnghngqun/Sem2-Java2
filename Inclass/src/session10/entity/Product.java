package session10.entity;

public class Product extends Entity <Integer>{
    private String ProductName;
    private double Price;
    private String description;

    public Product( String productName, double price, String description) {
        this.ProductName = productName;
        this.Price = price;
        this.description = description;
    }

    public Product() {
    }

    public Product(int id) {
        this.setId(id);
    }

    public Product(int id, String name, double price, String description) {
        this.setId(id);
        this.ProductName = name;
        this.Price = price;
        this.description = description;
    }

    public Product(String name) {
        this.ProductName = name;
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
