package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SupplyTest {

    @Test
    public void newSupply_instantiatesCorrectly() {
        Supply supply = setSupply();
        assertTrue(supply instanceof Supply);
    }

    @Test
    public void testEquals_suppliesWithSameFarmerProductIdQuantityPriceAreEqual() {
        Supply supply = setSupply();
        Supply otherSupply = setSupply();
        assertTrue(supply.equals(otherSupply));
    }

    @Test
    public void testHashCode_suppliesWithSameFarmerProductIdQuantityPriceAreEqual() {
        Supply supply = setSupply();
        Supply otherSupply = setSupply();
        assertTrue(supply.equals(otherSupply));
    }

    @Test
    public void setId_idIsSetCorrectly() {
        Supply supply = setSupply();
        supply.setId(1);
        assertEquals(1, supply.getId());
    }

    @Test
    public void getId_idInstantiatesCorrectly() {
        Supply supply = setSupply();
        supply.setId(1);
        assertEquals(1, supply.getId());
    }

    @Test
    public void getFarmerId_farmerIdInstantiatesCorrectly() {
        Supply supply = setSupply();
        assertEquals(1, supply.getFarmerId());
    }

    @Test
    public void getFarmerName_farmerNameInstantiatesCorrectly() {
        Supply supply = setSupply();
        assertEquals("kev", supply.getFarmerName());
    }

    @Test
    public void getProductId_productIdInstantiatesCorrectly() {
        Supply supply = setSupply();
        assertEquals(1, supply.getProductId());
    }

    @Test
    public void getProductName_productNameInstantiatesCorrectly() {
        Supply supply = setSupply();
        assertEquals("ken", supply.getProductName());
    }

    @Test
    public void getQuantity_quantityInstantiatesCorrectly() {
        Supply supply = setSupply();
        assertEquals(5, supply.getQuantity());
    }

    @Test
    public void getPrice_priceInstantiatesCorrectly() {
        Supply supply = setSupply();
        assertEquals(200, supply.getPrice());
    }

    @Test
    public void getTotalPrice_totalPriceInstantiatesCorrectly() {
        Supply supply = setSupply();
        assertEquals((5*supply.getPrice()), supply.getTotalPrice());
    }

    //helpers
    public Supply setSupply() {
        return new Supply (1, "kev", 1, "ken", 5, 200);
    }
}