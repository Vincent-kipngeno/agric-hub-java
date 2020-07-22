package dao;

import models.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Sql2oProductDaoTest {
    private static Sql2oCustomerDao customerDao;
    private static Sql2oFarmerDao farmerDao;
    private static Sql2oProductDao productDao;
    private static Sql2oSupplyDao supplyDao;
    private static Sql2oOrderDao orderDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/agric_hub_test";
        Sql2o sql2o = new Sql2o(connectionString, "vincent", "Taptet#2001");
        customerDao = new Sql2oCustomerDao(sql2o);
        farmerDao = new Sql2oFarmerDao(sql2o);
        productDao = new Sql2oProductDao(sql2o);
        supplyDao = new Sql2oSupplyDao(sql2o);
        orderDao = new Sql2oOrderDao(sql2o);
        conn = (Connection) sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing database");
        customerDao.clearAll();
        farmerDao.clearAll();
        productDao.clearAll();
        supplyDao.clearAll();
        orderDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("Connection closed");
    }
    @Test
    public void addingProductSetsId() {
        Product product = setProduct();
        int originalProductId = product.getId();
        productDao.add(product);
        assertNotEquals(originalProductId, product.getId());
    }

    @Test
    public void add_individualProductCanBeFoundById() {
        Product product = setProduct();
        productDao.add(product);
        Product foundProduct = productDao.findById(product.getId());
        assertEquals(product, foundProduct);
    }

    @Test
    public void findById_individualProductCanBeFoundById() {
        Product product = setProduct();
        productDao.add(product);
        Product foundProduct = productDao.findById(product.getId());
        assertEquals(product, foundProduct);
    }

    @Test
    public void getAll_allProductsAreReturnedCorrectly() {
        Product product = setProduct();
        productDao.add(product);
        assertEquals(1, productDao.getAll().size());
    }

    @Test
    public void getAll_nothingIsReturnedFromAnEmptyDatabase() {
        assertEquals(0, productDao.getAll().size());
    }

    @Test
    public void update_productIsUpdatedCorrectly() {
        Product product = setProduct();
        productDao.add(product);
        int currentId = product.getId();
        productDao.update(currentId, "apples");
        Product updatedProduct = productDao.findById(currentId);
        assertNotEquals(product, updatedProduct);
    }

    @Test
    public void deleteById_individualProductIsDeletedCorrectlyByItsId() {
        Product product = setProduct();
        Product otherProduct = setProduct();
        productDao.add(product);
        productDao.add(otherProduct);
        productDao.deleteById(product.getId());
        assertEquals(1, productDao.getAll().size());
    }

    @Test
    public void clearAll_allAddedProductsCanBeCleared() {
        Product product = setProduct();
        Product otherProduct = setProduct();
        productDao.add(product);
        productDao.add(otherProduct);
        productDao.clearAll();
        assertEquals(0, productDao.getAll().size());
    }
    @Test
    public void getAllSupplies_allSuppliesMadeForProductCanBeRetrieved() {
        Farmer farmer = new Farmer("henry", "nike", "hen@gmail.com");
        farmerDao.add(farmer);
        Product product = new Product("mangoes");
        productDao.add(product);
        Supply supply = new Supply(farmer.getId(), product.getId(), 4, 200);
        supplyDao.add(supply);
        Supply anotherSupply = new Supply(farmer.getId(), product.getId(), 4, 600);
        supplyDao.add(anotherSupply);
        List<Supply> supplies = productDao.getAllSuppliesByProductId(product.getId());
        assertTrue(supplies.contains(supply));
        assertTrue(supplies.contains(anotherSupply));
        assertEquals(2, supplies.size());
    }

    @Test
    public void getAllOrders_allOrdersMadeForProductCanBeRetrieved() {
        Farmer farmer = new Farmer("henry", "nike", "hen@gmail.com");
        farmerDao.add(farmer);
        Customer customer = new Customer("henken", "mom", "hen@gmail.com");
        customerDao.add(customer);
        Product product = new Product("mangoes");
        productDao.add(product);
        Supply supply = new Supply(farmer.getId(), product.getId(), 4, 100);
        Supply supply1 = new Supply(farmer.getId(), product.getId(), 6, 100);
        supplyDao.add(supply);
        supplyDao.add(supply1);
        List<Supply> supplies = new ArrayList<>();
        supplies.add(supply);
        supplies.add(supply1);
        Order order = new Order (customer.getId(), product.getId(), 4, supplies);
        orderDao.add(order);
        Order anotherOrder = new Order (customer.getId(), product.getId(), 4, supplies);
        orderDao.add(anotherOrder);
        List<Order> orders = productDao.getAllOrdersByProductId(product.getId());
        assertTrue(orders.contains(order));
        assertTrue(orders.contains(anotherOrder));
        assertEquals(2, orders.size());
    }

    public Product setProduct(){
        return new Product("mangoes");
    }
}