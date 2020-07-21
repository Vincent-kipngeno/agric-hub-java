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

import static org.junit.Assert.*;

public class Sql2oSupplyDaoTest {
    private static Sql2oFarmerDao farmerDao;
    private static Sql2oSupplyDao supplyDao;
    private static Sql2oProductDao productDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/agric_hub_test";
        Sql2o sql2o = new Sql2o(connectionString, "vincent", "Taptet#2001");
        farmerDao = new Sql2oFarmerDao(sql2o);
        supplyDao = new Sql2oSupplyDao(sql2o);
        productDao = new Sql2oProductDao(sql2o);
        conn = (Connection) sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing database");
        farmerDao.clearAll();
        supplyDao.clearAll();
        productDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("Connection closed");
    }
    @Test
    public void addingSupplySetsId() {
        Supply supply = setSupply();
        int originalSupplyId = supply.getId();
        supplyDao.add(supply);
        assertNotEquals(originalSupplyId, supply.getId());
    }

    @Test
    public void add_individualSupplyCanBeFoundById() {
        Supply supply = setSupply();
        supplyDao.add(supply);
        Supply foundSupply = supplyDao.findById(supply.getId());
        assertEquals(supply, foundSupply);
    }

    @Test
    public void findById_individualSupplyCanBeFoundById() {
        Supply supply = setSupply();
        supplyDao.add(supply);
        Supply foundSupply = supplyDao.findById(supply.getId());
        assertEquals(supply, foundSupply);
    }

    @Test
    public void getAll_allSuppliesAreReturnedCorrectly() {
        Supply supply = setSupply();
        supplyDao.add(supply);
        assertEquals(1, supplyDao.getAll().size());
    }

    @Test
    public void getAll_nothingIsReturnedFromAnEmptyDatabase() {
        assertEquals(0, supplyDao.getAll().size());
    }

    @Test
    public void update_supplyIsUpdatedCorrectly() {
        Supply supply = setSupply();
        supplyDao.add(supply);
        int currentId = supply.getId();
        Farmer farmerA = new Farmer("ken", "mumbai", "ken@gmail.com");
        farmerDao.add(farmerA);
        Product productA = new Product("apple");
        productDao.add(productA);
        supplyDao.update(currentId, farmerA.getId(), productA.getId(), 4, 200);
        Supply updatedSupply = supplyDao.findById(currentId);
        assertNotEquals(supply, updatedSupply);
    }

    @Test
    public void deleteById_individualSupplyIsDeletedCorrectlyByItsId() {
        Supply supply = setSupply();
        Supply otherSupply = setSupply();
        supplyDao.add(supply);
        supplyDao.add(otherSupply);
        supplyDao.deleteById(supply.getId());
        assertEquals(1, supplyDao.getAll().size());
    }

    @Test
    public void clearAll_allAddedSuppliesCanBeCleared() {
        Supply supply = setSupply();
        Supply otherSupply = setSupply();
        supplyDao.add(supply);
        supplyDao.add(otherSupply);
        supplyDao.clearAll();
        assertEquals(0, supplyDao.getAll().size());
    }


    //Helpers
    public Supply setSupply(){
        Farmer farmer = new Farmer("henry", "nike", "hen@gmail.com");
        farmerDao.add(farmer);
        Product product = new Product("mangoes");
        productDao.add(product);
        return new Supply(farmer.getId(), product.getId(), 4, 200);
    }
}