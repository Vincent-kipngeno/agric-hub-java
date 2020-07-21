package models;

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
