package dao;

import models.Order;
import models.Product;
import models.Supply;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oProductDao implements ProductDao {
    private final Sql2o sql2o;

    public Sql2oProductDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Product product) {
        String sql = "INSERT INTO products (name) VALUES (:name);";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(product)
                    .executeUpdate()
                    .getKey();
            product.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM products;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Product.class);
        }
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE id = :id ;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Product.class);
        }
    }

    @Override
    public void update(int id, String name) {
        String sql = "UPDATE products SET name = :name WHERE id = :id; ";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM products WHERE id = :id ;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM products;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }

    @Override
    public List<Supply> getAllSuppliesByProductId(int productId) {
        String sql = "SELECT * FROM supplies WHERE productid = :productId;";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("productId", productId)
                    .executeAndFetch(Supply.class);
        }
    }

    @Override
    public List<Order> getAllOrdersByProductId(int productId) {
        String sql = "SELECT * FROM orders WHERE productid = :productId;";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("productId", productId)
                    .executeAndFetch(Order.class);
        }
    }
}
