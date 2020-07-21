package dao;

import models.Supply;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSupplyDao implements SupplyDao{
    private final Sql2o sql2o;

    public Sql2oSupplyDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Supply supply) {
        String sql = "INSERT INTO supplies (farmerid, productid, quantity, price) VALUES (:farmerId, :productId, :quantity, :price);";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(supply)
                    .executeUpdate()
                    .getKey();
            supply.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Supply> getAll() {
        String sql = "SELECT * FROM supplies;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Supply.class);
        }
    }

    @Override
    public Supply findById(int id) {
        String sql = "SELECT * FROM supplies WHERE id = :id;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Supply.class);
        }
    }

    @Override
    public void update(int id, int farmerId, int productId, int quantity, int price) {
        String sql = "UPDATE supplies SET (farmerid, productid, quantity, price) = (:farmerId, :productId, :quantity, :price) WHERE id = :id; ";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("farmerId", farmerId)
                    .addParameter("productId", productId)
                    .addParameter("quantity", quantity)
                    .addParameter("price", price)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM supplies WHERE id = :id ;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM supplies;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }
}
