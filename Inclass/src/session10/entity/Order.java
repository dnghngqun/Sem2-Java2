package session10.entity;

import java.util.Date;

public class Order {
    private int id;
    private int customerId;
    private Date orderDate;
    private double totalAmount;
    private int status;
    private String statusString;



    public Order(int customerId, int id) {
        this.customerId = customerId;
        this.id = id;
    }

    public Order(int id, int customerId, Date orderDate, double totalAmount, int status) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order(int id, int customerId, int status) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
    }

    public Order(int id, int customerId, Date orderDate, double totalAmount, String statusString) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.statusString = statusString;
    }

    public Order() {
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString() {
        return statusString = status == 0 ? "Cancelled" : status == 1 ? "Pending" : "Completed";
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
