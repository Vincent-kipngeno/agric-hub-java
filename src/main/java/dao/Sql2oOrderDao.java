package dao;

import models.Order;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oOrderDao implements OrderDao {
    private final Sql2o sql2o;

    public Sql2oOrderDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Order order) {
        String sql = "INSERT INTO orders (customerid, customername, productid, productname, quantity) VALUES (:customerId, :customerName, :productId, :productName, :quantity);";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(order)
                    .executeUpdate()
                    .getKey();
            order.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM orders;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Order.class);
        }
    }

    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM orders WHERE id = :id;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Order.class);
        }
    }

    @Override
    public void update(int id, int customerId, String customerName, int productId, String productName, int quantity, int price) {
        String sql = "UPDATE orders SET (customerid, customername, productid, productname, quantity) = (:customerId, :customerName, :productId, :productName, :quantity) WHERE id = :id; ";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("customerId", customerId)
                    .addParameter("productId", productId)
                    .addParameter("quantity", quantity)
                    .addParameter("productName", productName)
                    .addParameter("customerName", customerName)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM orders WHERE id = :id ;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM orders;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }
}
