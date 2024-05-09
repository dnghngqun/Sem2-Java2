package session10.entity;

import java.util.Date;

public class Order extends  Entity<Integer>{
    private int customerId;
    private Date orderDate;
    private double totalAmount;
    private int status;
    private String statusString;
    private String customerName;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Order(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Order(int id, int customerId) {
        this.setId(id);
        this.customerId = customerId;
    }

    public Order(int id, int customerId, Date orderDate, double totalAmount, int status) {
        this.setId(id);
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order(int id, int customerId, int status) {
        this.setId(id);
        this.customerId = customerId;
        this.status = status;
    }

    public Order(int id, int customerId, double totalAmount, int status) {
        this.setId(id);
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order(int id, int customerId, Date orderDate, double totalAmount, String statusString) {
        this.setId(id);
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
        if(statusString != null) return statusString;
        switch (status) {
            case 1:
                return this.statusString = "Pending";
            case 2:
                return this.statusString = "Completed";
            case 0:
                return this.statusString = "Cancelled";
            default:
                return statusString;
        }
    }
    public int getStatus() {
        return status;
    }




    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
