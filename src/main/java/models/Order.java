package models;

import java.util.List;
import java.util.Objects;
import dao.*;

public class Order {
    private int id;
    private int customerId;
    private String customerName;
    private int productId;
    private String productName;
    private int quantity;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                customerId == order.customerId &&
                productId == order.productId &&
                quantity == order.quantity &&
                Objects.equals(customerName, order.customerName) &&
                Objects.equals(productName, order.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, customerName, productId, productName, quantity);
    }

    public Order(int customerId, String customerName, int productId, String productName, int quantity, List<Supply> supplies) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.customerName = customerName;
        this.productName = productName;
        setPrice(supplies);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
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

    public int getPrice() {
        return price;
    }
}
