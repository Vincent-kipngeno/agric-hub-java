package dao;

import models.Order;

import java.util.List;

public interface OrderDao {
    //create
    void add (Order order);

    //read
    List<Order> getAll();
    Order findById(int id);

    //update
    void update(int id, int customerId, int productId, int quantity, int price);

    //delete
    void deleteById(int id);
    void clearAll();
}
