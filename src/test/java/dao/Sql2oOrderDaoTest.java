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

public class Sql2oOrderDaoTest {
    private static Sql2oFarmerDao farmerDao;
    private static Sql2oSupplyDao supplyDao;
    private static Sql2oProductDao productDao;
    private static Sql2oCustomerDao customerDao;
    private static Sql2oOrderDao orderDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/agric_hub_test";
        Sql2o sql2o = new Sql2o(connectionString, "maureenbett", "kenyan082bett");
        farmerDao = new Sql2oFarmerDao(sql2o);
        supplyDao = new Sql2oSupplyDao(sql2o);
        productDao = new Sql2oProductDao(sql2o);
        customerDao = new Sql2oCustomerDao(sql2o);
        orderDao = new Sql2oOrderDao(sql2o);
        conn = (Connection) sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing database");
        farmerDao.clearAll();
        supplyDao.clearAll();
        productDao.clearAll();
        customerDao.clearAll();
        orderDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("Connection closed");
    }
    @Test
    public void addingOrderSetsId() {
        Order order = setOrder();
        int originalOrderId = order.getId();
        orderDao.add(order);
        assertNotEquals(originalOrderId, order.getId());
    }

    @Test
    public void add_individualOrderCanBeFoundById() {
        Order order = setOrder();
        orderDao.add(order);
        Order foundOrder = orderDao.findById(order.getId());
        assertEquals(order, foundOrder);
    }

    @Test
    public void findById_individualOrderCanBeFoundById() {
        Order order = setOrder();
        orderDao.add(order);
        Order foundOrder = orderDao.findById(order.getId());
        assertEquals(order, foundOrder);
    }

    @Test
    public void getAll_allOrdersAreReturnedCorrectly() {
        Order order = setOrder();
        orderDao.add(order);
        assertEquals(1, orderDao.getAll().size());
    }

    @Test
    public void getAll_nothingIsReturnedFromAnEmptyDatabase() {
        assertEquals(0, orderDao.getAll().size());
    }

    @Test
    public void update_orderIsUpdatedCorrectly() {
        Order order = setOrder();
        orderDao.add(order);
        int currentId = order.getId();
        Farmer farmerA = new Farmer("ken", "mumbai", "ken@gmail.com");
        farmerDao.add(farmerA);
        Customer customerA = new Customer("Kevinia", "mom", "hen@gmail.com");
        customerDao.add(customerA);
        Product productA = new Product("pineaple");
        productDao.add(productA);
        Supply supplyB = new Supply(farmerA.getId(), productA.getId(), 4, 500);
        Supply supplyD = new Supply(farmerA.getId(), productA.getId(), 6, 900);
        supplyDao.add(supplyB);
        supplyDao.add(supplyD);
        List<Supply> suppliesA = new ArrayList<>();
        suppliesA.add(supplyB);
        suppliesA.add(supplyB);
        order.setPrice(suppliesA);
        orderDao.update(currentId, customerA.getId(), productA.getId(), 4, order.getPrice());
        Order updatedOrder = orderDao.findById(currentId);
        assertNotEquals(order, updatedOrder);
    }

    @Test
    public void deleteById_individualOrderIsDeletedCorrectlyByItsId() {
        Order order = setOrder();
        Order otherOrder = setOrder();
        orderDao.add(order);
        orderDao.add(otherOrder);
        orderDao.deleteById(order.getId());
        assertEquals(1, orderDao.getAll().size());
    }

    @Test
    public void clearAll_allAddedOrdersCanBeCleared() {
        Order order = setOrder();
        Order otherOrder = setOrder();
        orderDao.add(order);
        orderDao.add(otherOrder);
        orderDao.clearAll();
        assertEquals(0, orderDao.getAll().size());
    }


    //Helpers
    public Order setOrder(){
        Farmer farmer = new Farmer("henry", "nike", "hen@gmail.com");
        farmerDao.add(farmer);
        Customer customer = new Customer("henken", "mom", "hen@gmail.com");
        customerDao.add(customer);
        Product product = new Product("mangoes");
        productDao.add(product);
        Supply supply = new Supply(farmer.getId(), product.getId(), 4, 200);
        Supply supply1 = new Supply(farmer.getId(), product.getId(), 6, 400);
        supplyDao.add(supply);
        supplyDao.add(supply1);
        List<Supply> supplies = new ArrayList<>();
        supplies.add(supply);
        supplies.add(supply1);
        return new Order (customer.getId(), product.getId(), 4, supplies);
    }
}