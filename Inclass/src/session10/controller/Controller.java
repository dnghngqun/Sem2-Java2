package session10.controller;

import session10.entity.Customer;
import session10.entity.Entity;
import session10.entity.OrderDetail;
import session10.entity.Product;
import session10.model.CustomerDAOImpl;
import session10.model.Model;
import session10.model.OrderManagementImpl;
import session10.model.ProductDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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
   public List<T> getEntityById(T entity) throws SQLException {
       return model.getEntityById(entity);
   }


}
