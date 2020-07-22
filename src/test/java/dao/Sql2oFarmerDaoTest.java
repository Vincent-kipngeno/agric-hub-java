package dao;

import models.Farmer;
import models.Product;
import models.Supply;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oFarmerDaoTest {
    private static Sql2oFarmerDao farmerDao;
    private static Sql2oProductDao productDao;
    private static Sql2oSupplyDao supplyDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/agric_hub_test";
        Sql2o sql2o = new Sql2o(connectionString, "vincent", "Taptet#2001");
        farmerDao = new Sql2oFarmerDao(sql2o);
        productDao = new Sql2oProductDao(sql2o);
        supplyDao = new Sql2oSupplyDao(sql2o);
        conn = (Connection) sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing database");
        farmerDao.clearAll();
        productDao.clearAll();
        supplyDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("Connection closed");
    }
    @Test
    public void addingFarmerSetsId() {
        Farmer farmer = setFarmer();
        int originalFarmerId = farmer.getId();
        farmerDao.add(farmer);
        assertNotEquals(originalFarmerId, farmer.getId());
    }

    @Test
    public void add_individualFarmerCanBeFoundById() {
        Farmer farmer = setFarmer();
        farmerDao.add(farmer);
        Farmer foundFarmer = farmerDao.findById(farmer.getId());
        assertEquals(farmer, foundFarmer);
    }

    @Test
    public void findById_individualFarmerCanBeFoundById() {
        Farmer farmer = setFarmer();
        farmerDao.add(farmer);
        Farmer foundFarmer = farmerDao.findById(farmer.getId());
        assertEquals(farmer, foundFarmer);
    }

    @Test
    public void getAll_allFarmersAreReturnedCorrectly() {
        Farmer farmer = setFarmer();
        farmerDao.add(farmer);
        assertEquals(1, farmerDao.getAll().size());
    }

    @Test
    public void getAll_nothingIsReturnedFromAnEmptyDatabase() {
        assertEquals(0, farmerDao.getAll().size());
    }

    @Test
    public void update_farmerIsUpdatedCorrectly() {
        Farmer farmer = setFarmer();
        farmerDao.add(farmer);
        int currentId = farmer.getId();
        farmerDao.update(currentId, "kevin", "UG", "kipkev@gmail.com");
        Farmer updatedFarmer = farmerDao.findById(currentId);
        assertNotEquals(farmer, updatedFarmer);
    }

    @Test
    public void deleteById_individualFarmerIsDeletedCorrectlyByItsId() {
        Farmer farmer = setFarmer();
        Farmer otherFarmer = setFarmer();
        farmerDao.add(farmer);
        farmerDao.add(otherFarmer);
        farmerDao.deleteById(farmer.getId());
        assertEquals(1, farmerDao.getAll().size());
    }

    @Test
    public void clearAll_allAddedFarmersCanBeCleared() {
        Farmer farmer = setFarmer();
        Farmer otherFarmer = setFarmer();
        farmerDao.add(farmer);
        farmerDao.add(otherFarmer);
        farmerDao.clearAll();
        assertEquals(0, farmerDao.getAll().size());
    }

    @Test
    public void getAllSupplies_allSuppliesMadeByFarmersCanBeRetrieved() {
        Farmer farmer = setFarmer();
        farmerDao.add(farmer);
        Product product = new Product("mangoes");
        productDao.add(product);
        Supply supply = new Supply(farmer.getId(), product.getId(), 4, 200);
        supplyDao.add(supply);
        Supply anotherSupply = new Supply(farmer.getId(), product.getId(), 4, 600);
        supplyDao.add(anotherSupply);
        List<Supply> supplies = farmerDao.getAllSuppliesByFarmerId(farmer.getId());
        assertTrue(supplies.contains(supply));
        assertTrue(supplies.contains(anotherSupply));
        assertEquals(2, supplies.size());
    }

    public Farmer setFarmer(){
        return new Farmer("henry", "nike", "hen@gmail.com");
    }
}