package dao;

import models.Product;

import java.util.List;

public interface ProductDao {
    //create
    void add (Product product);

    //read
    List<Product> getAll();
    Product findById(int id);

    //update
    void update(int id, String name);

    //delete
    void deleteById(int id);
    void clearAll();
}
