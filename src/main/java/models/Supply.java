package models;

public class Supply {
    private int id;
    private int customerId;
    private int productId;
    private int quantity;
    private int price;
    private int totalPrice;

    public Supply(int customerId, int productId, int quantity, int price) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        setTotalPrice();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalPrice() {
        this.totalPrice = price * quantity;
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

    public int getPrice() {
        return price;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
