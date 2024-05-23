package ExJsonDatabase.controller;

import ExJsonDatabase.entity.Entity;
import ExJsonDatabase.entity.Order;
import ExJsonDatabase.entity.OrderDetail;
import ExJsonDatabase.model.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller<T extends Entity<?>> {
    Model<T> model = new Model<>();



   public void addEntity(T entity) throws SQLException, IllegalAccessException {
       model.addEntity(entity);
   }

   public void removeEntity(T entity) throws SQLException, IllegalAccessException {
       boolean rs = model.deleteEntity(entity);
       if (!rs) {
           System.err.println("Failed to delete!");
       }
   }

   public void updateEntity(T entity) throws SQLException, IllegalAccessException {
       model.updateEntity(entity);
   }

   public List<T> getAllEntities(T entity) throws SQLException {
       return model.getAllEntities(entity);
   }
   public T getEntityById(T entity) throws SQLException {
       return model.getEntityById(entity);
   }

   public List<T> getAllEntitiesByName(T entity) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
       return model.getAllEntitiesByName(entity);
   }

    public void addOrder(Order order, List<OrderDetail> orderDetail) throws SQLException {
        for(OrderDetail od: orderDetail){
            od.setTotalPrice((od.getUnitPrice() - od.getUnitPrice() * od.getDiscount())* od.getQuantity());
        }
        model.addOrder(order, orderDetail);
    }

    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        return model.getOrdersByCustomerId(customerId);
    }

    public List<OrderDetail> showOrderDetailsByCustomerID(int id) throws SQLException {
        return model.showOrderDetailsByCustomerID(id);
    }

    public double getTotalPriceByCustomerId(int id) throws SQLException {
        return model.getTotalPriceByCustomerId(id);
    }

    public boolean updateOrderStatus(int orderId, int status) throws SQLException {
        Order order = new Order();
        order.convertStatus(status);
        System.out.println("Test: " + order.getStatus());

        boolean result = model.updateOrderStatus(orderId, order.getStatus());
        if(result) {
            System.out.println("Order with ID " + orderId + " has been successfully updated.");
            return true;
        }else {
            System.out.println("Failed to update order with ID " + orderId + ".");
            System.out.println("Please try again.");
            return false;
        }

    }

    public boolean deleteOrder(int id) throws SQLException {
        boolean result = model.deleteOrder(id);
        if(result){
            System.out.println("Order with ID " + id + " has been successfully removed.");
            return true;
        }else {
            return false;
        }
    }



}
