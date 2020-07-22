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
        get("/farmers/delete", (req, res) -> {
           Map<String, Object> models = new HashMap<>();
           farmerDao.clearAll();
           res.redirect("/");
           return null;
        }, new HandlebarsTemplateEngine());

        //get: delete a farmer entry together with supplies made by the farmer
        get("/farmers/:id/delete", (req, res) -> {
           Map<String, Object> models = new HashMap<>();
           int farmerId = Integer.parseInt(req.params("id"));
           farmerDao.deleteById(farmerId);
           res.redirect("/");
           return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all supplies
        get("/supplies/delete", (req, res) -> {
           Map<String, Object> models = new HashMap<>();
           supplyDao.clearAll();
           res.redirect("/");
           return null;
        }, new HandlebarsTemplateEngine());

        //get: display details of a farmer together with supplies made by the farmer.
        get("/farmers/:id", (req, res) -> {
           Map<String, Object> models = new HashMap<>();
           int farmerId = Integer.parseInt(req.params("id"));
           Farmer farmerToFind = farmerDao.findById(farmerId);
           models.put("farmer", farmerToFind);
           models.put("supplies", farmerDao.getAllSuppliesByFarmerId(farmerId));
           List<Farmer> farmers = farmerDao.getAll();
           models.put("farmers", farmers);
           models.put("customers", customerDao.getAll());
           models.put("products", productDao.getAll());
           return new ModelAndView(models, "farmer-details.hbs");
        }, new HandlebarsTemplateEngine());

        //get: get form to update a farmer
        get("/farmers/:id/edit", (req, resp) -> {
           Map<String, Object> models = new HashMap<>();
           int farmerId = Integer.parseInt(req.params("id"));
           models.put("editFarmer", farmerDao.findById(farmerId));
           List<Farmer> farmers = farmerDao.getAll();
           models.put("farmers", farmers);
           models.put("customers", customerDao.getAll());
           models.put("products", productDao.getAll());
           return new ModelAndView(models, "farmer-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: update a farmer's details
        post("/farmers/:id", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int farmerId = Integer.parseInt(req.params("id"));
            String name = req.queryParams("name");
            String location = req.queryParams("location");
            String email = req.queryParams("email");
            farmerDao.update(farmerId, name, location, email);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

                         ////Supplies
        //get a form to Create a Supply instance
        get("/supplies/new", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            models.put("farmers", farmerDao.getAll());
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "supply-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: Create a Supply instance
        post("/supplies", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int farmerId = Integer.parseInt(req.queryParams("farmerId"));
            String farmerName = farmerDao.findById(farmerId).getName();
            int productId = Integer.parseInt(req.queryParams("productId"));
            String productName = productDao.findById(productId).getName();
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            int price = Integer.parseInt(req.queryParams("price"));
            Supply supply = new Supply(farmerId, farmerName, productId, productName, quantity, price );
            supplyDao.add(supply);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete individual supply
        get("/farmers/:farmerId/products/:productId/supplies/:id/delete", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int supplyId = Integer.parseInt(req.params("id"));
            supplyDao.deleteById(supplyId);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: get list of all supplies
        get("/supplies", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            models.put("supplies", supplyDao.getAll());
            models.put("farmers", farmerDao.getAll());
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "supplies.hbs");
        }, new HandlebarsTemplateEngine());

        //get: display details of individual supplies made with name of farmer and product.
        get("/farmers/:farmerId/products/:productId/supplies/:id", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int supplyId = Integer.parseInt(req.params("id"));
            int farmerId = Integer.parseInt(req.params("farmerId"));
            int productId = Integer.parseInt(req.params("productId"));
            models.put("supply", supplyDao.findById(supplyId));
            models.put("farmer", farmerDao.findById(farmerId));
            models.put("product", productDao.findById(productId));
            models.put("farmers", farmerDao.getAll());
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "supply-details.hbs");
        }, new HandlebarsTemplateEngine());

        //get: get form to update a supply
        get("/supplies/:id/edit", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int supplyId = Integer.parseInt(req.params("id"));
            models.put("editSupply", supplyDao.findById(supplyId));

            models.put("farmers", farmerDao.getAll());
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "supply-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: update supply details
        post("/supplies/:id", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int supplyId = Integer.parseInt(req.params("id"));
            int farmerId = Integer.parseInt(req.queryParams("farmerId"));
            String farmerName = farmerDao.findById(farmerId).getName();
            int productId = Integer.parseInt(req.queryParams("productId"));
            String productName = productDao.findById(productId).getName();
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            int price = Integer.parseInt(req.queryParams("price"));
            supplyDao.update(supplyId, farmerId, farmerName, productId, productName, quantity, price);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


                        /////Products

        //get a form to Create a product instance
        get("/products/new", (req, res) -> {
            Map<String, Object> models = new HashMap<>();

            models.put("farmers", farmerDao.getAll());
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "product-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: Create a Product instance
        post("/products", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            String name = req.queryParams("name");
            Product product = new Product(name);
            productDao.add(product);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all products
        get("/products/delete", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            productDao.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete a product entry together with supplies and orders made for that product and
        get("/products/:id/delete", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int productId = Integer.parseInt(req.params("id"));
            productDao.deleteById(productId);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: display details of a product together with supplies and orders made for that product.
        get("/products/:id", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int productId = Integer.parseInt(req.params("id"));
            models.put("product", productDao.findById(productId));
            models.put("supplies", productDao.getAllSuppliesByProductId(productId));
            models.put("orders", productDao.getAllOrdersByProductId(productId));

            List<Farmer> farmers = farmerDao.getAll();
            models.put("farmers", farmers);
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "product-details.hbs");
        }, new HandlebarsTemplateEngine());

        //get: get form to update a product
        get("/products/:id/edit", (req, resp) -> {
            Map<String, Object> models = new HashMap<>();
            int productId = Integer.parseInt(req.params("id"));
            models.put("editProduct", productDao.findById(productId));

            List<Farmer> farmers = farmerDao.getAll();
            models.put("farmers", farmers);
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "product-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: update a farmer's details
        post("/products/:id", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int productId = Integer.parseInt(req.params("id"));
            String name = req.queryParams("name");
            productDao.update(productId, name);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

                                ////Customers

        //get a form to Create a Customer instance (Customer makes the order of farm products)
        get("/customers/new", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
                //refresh navbar links
            models.put("farmers", farmerDao.getAll());
            models.put("customers", customerDao.getAll());
            models.put("products", productDao.getAll());

            return new ModelAndView(models, "customer-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: Create a customer instance
        post("/customers", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            String name = req.queryParams("name");
            String location = req.queryParams("location");
            String email = req.queryParams("email");
            Customer customer = new Customer(name, location, email);
            customerDao.add(customer);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: delete all customers
        get("/customers/delete", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            customerDao.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete a customer entry together with orders made by the customer
        get("/customers/:id/delete", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int customerId = Integer.parseInt(req.params("id"));
            customerDao.deleteById(customerId);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all orders
        get("/orders/delete", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            orderDao.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: display details of a customer together with orders made by the customer.
        get("/customers/:id", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int customerId = Integer.parseInt(req.params("id"));
            Customer customerToFind = customerDao.findById(customerId);
            models.put("customer", customerToFind);
            models.put("orders", customerDao.getAllOrdersByCustomerId(customerId));

            List<Customer> customers = customerDao.getAll();
            models.put("customers", customers);
            models.put("farmers", farmerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "customer-details.hbs");
        }, new HandlebarsTemplateEngine());


        //get: get form to update a customer
        get("/customers/:id/edit", (req, resp) -> {
            Map<String, Object> models = new HashMap<>();
            int customerId = Integer.parseInt(req.params("id"));
            models.put("editCustomer", customerDao.findById(customerId));

            List<Customer> customers = customerDao.getAll();
            models.put("customers", customers);
            models.put("farmers", farmerDao.getAll());
            models.put("products", productDao.getAll());
            return new ModelAndView(models, "customer-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: update a customer's details
        post("/customers/:id", (req, res) -> {
            Map<String, Object> models = new HashMap<>();
            int customerId = Integer.parseInt(req.params("id"));
            String name = req.queryParams("name");
            String location = req.queryParams("location");
            String email = req.queryParams("email");
            customerDao.update(customerId, name, location, email);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

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
