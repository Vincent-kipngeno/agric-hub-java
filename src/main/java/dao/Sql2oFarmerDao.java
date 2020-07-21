package dao;

import models.Farmer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oFarmerDao implements FarmerDao {
    private final Sql2o sql2o;

    public Sql2oFarmerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Farmer farmer) {
        String sql = "INSERT INTO farmers (name, location, email) VALUES (:name, :location, :email);";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(farmer)
                    .executeUpdate()
                    .getKey();
            farmer.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Farmer> getAll() {
        String sql = "SELECT * FROM farmers;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Farmer.class);
        }
    }

    @Override
    public Farmer findById(int id) {
        String sql = "SELECT * FROM farmers WHERE id = :id;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Farmer.class);
        }
    }

    @Override
    public void update(int id, String name, String location, String email) {
        String sql = "UPDATE farmers SET (name, location, email) = (:name, :location, :email) WHERE id = :id; ";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("location", location)
                    .addParameter("email", email)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM farmers WHERE id = :id ;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM farmers;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }
}
