package dao;

import models.Order;
import models.Product;
import models.Supply;

import java.util.List;

public interface ProductDao {
    //create
    void add (Product product);

    //read
    List<Product> getAll();
    Product findById(int id);
    List<Supply> getAllSuppliesByProductId(int productId);
    List<Order> getAllOrdersByProductId(int productId);

    //update
    void update(int id, String name);

    //delete
    void deleteById(int id);
    void clearAll();
}
