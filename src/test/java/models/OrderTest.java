package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void setId_idIsSetCorrectly() {
       Order order = setOrder();
       order.setId(1);
       assertEquals(1, order.getId());
    }

    @Test
    public void setPrice_priceIsSetCorrectly() {
        Order order = setOrder();
        int price = order.getTotalPrice();
        Supply supply = new Supply(1, 1, 5, 200);
        Supply otherSupply = new Supply(2, 1, 7, 300);
        List<Supply> supplies = new ArrayList<Supply>();
        supplies.add(supply);
        supplies.add(otherSupply);
        order.setPrice(supplies);
        assertEquals(price, order.getTotalPrice());
    }

    @Test
    public void getId_idIsSetAndRetrievedCorrectly() {
        Order order = setOrder();
        order.setId(1);
        assertEquals(1, order.getId());
    }

    @Test
    public void getCustomerId_customerIdIsRetrieved() {
        Order order = setOrder();
        assertEquals(1, order.getCustomerId());
    }

    @Test
    public void getCustomerName_customerNameIsRetrieved() {
        Order order = setOrder();
        assertEquals("kev", order.getCustomerName());
    }

    @Test
    public void getProductName_productNameIsRetrieved() {
        Order order = setOrder();
        assertEquals("ken", order.getProductName());
    }

    @Test
    public void getProductId_productIdIsRetrieved() {
        Order order = setOrder();
        assertEquals(1, order.getProductId());
    }

    @Test
    public void getQuantity_quantityIsRetrieved() {
        Order order = setOrder();
        assertEquals(4, order.getQuantity());
    }

    @Test
    public void getTotalPrice_totalPriceIsRetrieved() {
        Order order = setOrder();
        int price = order.getTotalPrice();
        Supply supply = new Supply(1, 1, 5, 200);
        Supply otherSupply = new Supply(2, 1, 7, 300);
        List<Supply> supplies = new ArrayList<Supply>();
        supplies.add(supply);
        supplies.add(otherSupply);
        order.setPrice(supplies);
        assertEquals(price, order.getTotalPrice());
    }

    @Test
    public void equals_ordersWithSameCustomerProductIdQuantityPriceAreEqual(){
        Order order = setOrder();
        Order otherOrder = setOrder();
        assertTrue(order.equals(otherOrder));
    }

    //helpers
    public Order setOrder(){
        Supply supply = new Supply(1, 1, 5, 200);
        Supply otherSupply = new Supply(2, 1, 7, 300);
        List<Supply> supplies = new ArrayList<Supply>();
        supplies.add(supply);
        supplies.add(otherSupply);
        return new Order(1, "kev", 1, "ken", 4, supplies);
    }
}