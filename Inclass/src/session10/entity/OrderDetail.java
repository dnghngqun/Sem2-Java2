package session10.entity;

public class OrderDetail extends Entity<Integer>{
    private int orderId;
    private int productId;
    private int quantity;
    private double unitPrice;
    private double discount = 0;
    private double totalPrice;
//    private String customerName;
//    private String productName;


    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }


    public OrderDetail(int productId, int quantity, double unitPrice, double discount) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public OrderDetail() {
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
        if(totalPrice != 0) return totalPrice;
        return totalPrice = unitPrice * quantity - unitPrice * discount;
    }

}
