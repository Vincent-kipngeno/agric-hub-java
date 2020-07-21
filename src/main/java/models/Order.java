package models;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                customerId == order.customerId &&
                productId == order.productId &&
                quantity == order.quantity &&
                price == order.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, productId, quantity, price);
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
