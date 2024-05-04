package session10.entity;

public class OrderDetail {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double unitPrice;
    private double discount;
    private double totalPrice;

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
