package dao;

import models.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCustomerDaoTest {
    private static Sql2oCustomerDao customerDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/agric_hub_test";
        Sql2o sql2o = new Sql2o(connectionString, "maureenbett", "kenyan082bett");
        customerDao = new Sql2oCustomerDao(sql2o);
        conn = (Connection) sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing database");
        customerDao.clearAll();
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

    public Customer setCustomer(){
        return new Customer("henry", "nike", "hen@gmail.com");
    }
}