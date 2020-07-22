package dao;

import models.Customer;
import models.Order;

import java.util.List;

public interface CustomerDao {
    //create
    void add (Customer customer);

    //read
    List<Customer> getAll();
    Customer findById(int id);
    List<Order> getAllOrdersByCustomerId(int id);

    //update
    void update(int id, String name, String location, String email);

    //delete
    void deleteById(int id);
    void clearAll();
}
