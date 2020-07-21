package dao;

import models.Farmer;
import models.Supply;

import java.util.List;

public interface FarmerDao {
    //create
    void add (Farmer farmer);

    //read
    List<Farmer> getAll();
    Farmer findById(int id);
    List<Supply> getAllSuppliesByFarmerId(int id);

    //update
    void update(int id, String name, String location, String email);

    //delete
    void deleteById(int id);
    void clearAll();
}
