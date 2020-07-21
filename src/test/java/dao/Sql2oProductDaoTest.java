package dao;

import models.Product;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oProductDaoTest {
    private static Sql2oProductDao productDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/agric_hub_test";
        Sql2o sql2o = new Sql2o(connectionString, "vincent", "Taptet#2001");
        productDao = new Sql2oProductDao(sql2o);
        conn = (Connection) sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Clearing database");
        productDao.clearAll();
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

    public Product setProduct(){
        return new Product("mangoes");
    }
}