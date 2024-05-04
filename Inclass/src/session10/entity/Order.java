package session10.entity;

import java.util.Date;

public class Order {
    private int id;
    private int customerId;
    private Date orderDate;
    private double totalAmount;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }



    public int getId() {
        return id;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
