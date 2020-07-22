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

public class Sql2oCustomerDaoTest {
    private static Sql2oCustomerDao customerDao;
    private static Sql2oFarmerDao farmerDao;
    private static Sql2oProductDao productDao;
    private static Sql2oSupplyDao supplyDao;
    private static Sql2oOrderDao orderDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/agric_hub_test";
        Sql2o sql2o = new Sql2o(connectionString, "maureenbett", "kenyan082bett");
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
        farmerDao.clearAll();
        orderDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("Connection closed");
    }
    @Test
    public void addingCustomerSetsId() {
        Customer customer = setCustomer();
        int originalCustomerId = customer.getId();
        customerDao.add(customer);
        assertNotEquals(originalCustomerId, customer.getId());
    }

    @Test
    public void add_individualCustomerCanBeFoundById() {
        Customer customer = setCustomer();
        customerDao.add(customer);
        Customer foundCustomer = customerDao.findById(customer.getId());
        assertEquals(customer, foundCustomer);
    }

    @Test
    public void findById_individualCustomerCanBeFoundById() {
        Customer customer = setCustomer();
        customerDao.add(customer);
        Customer foundCustomer = customerDao.findById(customer.getId());
        assertEquals(customer, foundCustomer);
    }

    @Test
    public void getAll_allCustomersAreReturnedCorrectly() {
        Customer customer = setCustomer();
        customerDao.add(customer);
        assertEquals(1, customerDao.getAll().size());
    }

    @Test
    public void getAll_nothingIsReturnedFromAnEmptyDatabase() {
        assertEquals(0, customerDao.getAll().size());
    }

    @Test
    public void update_customerIsUpdatedCorrectly() {
        Customer customer = setCustomer();
        customerDao.add(customer);
        int currentId = customer.getId();
        customerDao.update(currentId, "kevin", "UG", "kipkev@gmail.com");
        Customer updatedCustomer = customerDao.findById(currentId);
        assertNotEquals(customer, updatedCustomer);
    }

    @Test
    public void deleteById_individualCustomerIsDeletedCorrectlyByItsId() {
        Customer customer = setCustomer();
        Customer otherCustomer = setCustomer();
        customerDao.add(customer);
        customerDao.add(otherCustomer);
        customerDao.deleteById(customer.getId());
        assertEquals(1, customerDao.getAll().size());
    }

    @Test
    public void clearAll_allAddedCustomersCanBeCleared() {
        Customer customer = setCustomer();
        Customer otherCustomer = setCustomer();
        customerDao.add(customer);
        customerDao.add(otherCustomer);
        customerDao.clearAll();
        assertEquals(0, customerDao.getAll().size());
    }

    @Test
    public void getAllOrders_allOrdersMadeByFarmersCanBeRetrieved() {
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
        List<Order> orders = customerDao.getAllOrdersByCustomerId(customer.getId());
        assertTrue(orders.contains(order));
        assertTrue(orders.contains(anotherOrder));
        assertEquals(2, orders.size());
    }

    public Customer setCustomer(){
        return new Customer("henry", "nike", "hen@gmail.com");
    }
}