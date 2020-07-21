package dao;

import models.Customer;

import java.util.List;

public interface CustomerDao {
    //create
    void add (Customer customer);

    //read
    List<Customer> getAll();
    Customer findById(int id);

    //update
    void update(int id, String name, String location, String email);

    //delete
    void deleteById(int id);
    void clearAll();
}
