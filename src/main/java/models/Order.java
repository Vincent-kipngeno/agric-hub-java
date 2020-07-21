package models;

import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private int productId;
    private int quantity;
    private int price;

    public Order(int customerId, int productId, int quantity, List<Supply> supplies) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        setPrice(supplies);
    }

    public void setId(int id) {
        this.id = id;
    }


    //take all the supplies with the product id of this order loop through their prices and find total price then find the average
    public void setPrice(List<Supply> supplies) {
        int supplyPrice = 0;
        for (Supply supply : supplies) {
            supplyPrice += supply.getPrice();
        }
        this.price = supplyPrice / supplies.size();
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() { return quantity; }

    public int getTotalPrice() { return quantity * price; }
}
