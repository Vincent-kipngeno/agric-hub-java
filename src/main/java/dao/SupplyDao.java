package dao;

import models.Supply;

import java.util.List;

public interface SupplyDao {
    //create
    void add (Supply supply);

    //read
    List<Supply> getAll();
    Supply findById(int id);

    //update
    void update(int id, int farmerId, String farmerName, int productId, String productName, int quantity, int price);

    //delete
    void deleteById(int id);
    void clearAll();
}
