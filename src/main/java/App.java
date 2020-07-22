import models.*;
import dao.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oOrderDao orderDao;
        Sql2oSupplyDao supplyDao;
        Sql2oFarmerDao farmerDao;
        Sql2oCustomerDao customerDao;
        Sql2oProductDao productDao;
        Connection conn;

        String connectionString = "jdbc:postgresql://localhost:5432/agric_hub";
        Sql2o sql2o = new Sql2o(connectionString, "vincent", "Taptet#2001");
        orderDao = new Sql2oOrderDao(sql2o);
        supplyDao = new Sql2oSupplyDao(sql2o);
        farmerDao = new Sql2oFarmerDao(sql2o);
        customerDao = new Sql2oCustomerDao(sql2o);
        productDao = new Sql2oProductDao(sql2o);
        conn = sql2o.open();

        //get: view list of all customers, farmers, products in the navbar and list of supplies made in index.hbs
        get("/", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            List<Farmer> farmers = farmerDao.getAll();
            models.put("farmers", farmers);
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get a form to Create a Farmer instance (farmer is the supplier of farm products)
        get("/farmers/new", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            List<Farmer> farmers = farmerDao.getAll();
            models.put("farmers", farmers);
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "farmer-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: Create a Farmer instance (farmer is the supplier of farm products)
        post("/farmers", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            String name = req.queryParams("name");
            String location = req.queryParams("location");
            String email = req.queryParams("email");
            Farmer farmer = new Farmer(name, location, email);
            farmerDao.add(farmer);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: delete all farmers
        //get("/farmers/delete)

        //get: delete a farmer entry together with supplies made by the farmer
        //get("/farmers/:id/delete")

        //get: delete all supplies
        //get("/supplies/delete")

        //get: display details of a farmer together with supplies made by the farmer.
        //get("/farmers/:id")

        //get: get form to update a farmer
        //get("/farmers/:id/edit")

        //post: update a farmer's details
        //post("/farmers/:id")

                         ////Supplies
        //get a form to Create a Supply instance
        //get("/supplies/new")

        //post: Create a Supply instance
        //post("/supplies")

        //get: delete individual supply
        //get("/farmers/:farmerId/products/:productId/supplies/:id/delete")

        //get: display details of individual supplies made with name of farmer and product.
        //get("/farmers/:farmerId/products/:productId/supplies/:id")

        //get: get form to update a supply
        //get("/supplies/:id/edit")

        //post: update supply details
        //post("/supplies/:id")


                        /////Products

        //get a form to Create a product instance
        //get("/products/new")

        //post: Create a Product instance
        //post("/products")

        //get: delete all products
        //get("/products/delete)

        //get: delete a product entry together with supplies and orders made for that product and
        //get("/products/:id/delete")

        //get: display details of a product together with supplies and orders made for that product.
        //get("/products/:id")

        //get: get form to update a product
        //get("/products/:id/edit")

        //post: update a farmer's details
        //post("/products/:id")

                                ////Customers

        //get a form to Create a Customer instance (Customer makes the order of farm products)
        //get("/customers/new")

        //post: Create a customer instance
        //post("/customers")

        //get: delete all customers
        //get("/customers/delete)

        //get: delete a customer entry together with orders made by the customer
        //get("/customers/:id/delete")

        //get: delete all orders
        //get("/orders/delete")

        //get: display details of a customer together with orders made by the customer.
        //get("/customers/:id")

        //get: get form to update a customer
        //get("/customers/:id/edit")

        //post: update a customer's details
        //post("/customers/:id")

                                ////orders

        //get a form to Create an order instance
        //get("/orders/new")

        //post: Create an order instance
        //post("/orders")

        //get: delete individual order
        //get("/customers/:customerId/products/:productId/orders/:id/delete")

        //get: display details of individual orders made with name of customer and product.
        //get("/customers/:customerId/products/:productId/orders/:id")

        //get: get form to update an order
        //get("/orders/:id/edit")

        //post: update order details
        //post("/orders/:id")
    }
}
