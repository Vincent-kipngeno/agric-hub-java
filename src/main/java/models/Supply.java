package models;

import java.util.Objects;

public class Supply {
    private int id;
    private int farmerId;
    private String farmerName;
    private int productId;
    private String productName;
    private int quantity;
    private int price;

    public Supply(int farmerId, String farmerName, int productId, String productName, int quantity, int price) {
        this.farmerId = farmerId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.farmerName = farmerName;
        this.productName = productName;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supply order = (Supply) o;
        return id == order.id &&
                farmerId == order.farmerId &&
                productId == order.productId &&
                quantity == order.quantity &&
                price == order.price &&
                Objects.equals(farmerName, order.farmerName) &&
                Objects.equals(productName, order.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, farmerId, farmerName, productId, productName, quantity, price);
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}
