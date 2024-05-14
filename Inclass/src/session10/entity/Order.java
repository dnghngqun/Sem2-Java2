package session10.entity;

import java.util.Date;

public class Order extends  Entity<Integer>{
    private int customerId;
    private Date orderDate;
    private double totalAmount;
    private String status;


    public Order(int customerId) {
        this.customerId = customerId;
    }





    public Order(int customerId, int StatusNumber) {
        this.customerId = customerId;
        convertStatus(StatusNumber);
    }

    public Order(int id, int customerId, Date orderDate, double totalAmount, int statusNumber) {
        this.setId(id);
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        convertStatus(statusNumber);
    }


    public Order() {
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void convertStatus(int statusNumber) {
        switch (statusNumber) {
            case 1:
                status = "Pending";
                break;
            case 2:
                status = "Completed";
                break;
            case 0:
                status = "Cancelled";
                break;
            default:
                status = null;
                break;
        }
    }

    public String getStatus() {
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
