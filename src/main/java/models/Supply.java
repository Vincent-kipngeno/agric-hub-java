package models;

import java.util.Objects;

public class Supply {
    private int id;
    private int farmerId;
    private int productId;
    private int quantity;
    private int price;

    public Supply(int farmerId, int productId, int quantity, int price) {
        this.farmerId = farmerId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supply supply = (Supply) o;
        return id == supply.id &&
                farmerId == supply.farmerId &&
                productId == supply.productId &&
                quantity == supply.quantity &&
                price == supply.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, farmerId, productId, quantity, price);
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
