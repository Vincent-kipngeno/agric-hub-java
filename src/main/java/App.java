public class App {
    public static void main(String[] args) {


        //get: view list of all customers, farmers, products in the navbar and list of supplies made in index.hbs
        //get("/")

        //get a form to Create a Farmer instance (farmer is the supplier of farm products)
        //get("/farmers/new")

        //post: Create a Farmer instance (farmer is the supplier of farm products)
        //post("/farmers")

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
