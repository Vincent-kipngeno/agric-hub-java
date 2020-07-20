package models;

public class Order {
    private int id;
    private int customerId;
    private int productId;
    private int quantity;
    private int totalPrice;

    public Order(int customerId, int productId, int quantity, Supply supply) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        setTotalPrice(supply);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalPrice(Supply supply) {
        this.totalPrice = supply.getPrice() * this.quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
