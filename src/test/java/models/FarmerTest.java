package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class FarmerTest {

    @Test
    public void newFarmer_instantiatesCorrectly() {
        Farmer farmer = setFarmer();
        assertTrue(farmer instanceof Farmer);
    }
    @Test
    public void testEquals_farmerWithSameNameLocationEmailAreEqual() {
        Farmer farmer = setFarmer();
        Farmer otherFarmer = setFarmer();
        assertTrue(farmer.equals(otherFarmer));
    }

    @Test
    public void testHashCode_farmerWithSameNameLocationEmailAreEqual() {
        Farmer farmer = setFarmer();
        Farmer otherFarmer = setFarmer();
        assertTrue(farmer.equals(otherFarmer));
    }

    @Test
    public void setId_isSetCorrectly() {
        Farmer farmer = setFarmer();
        farmer.setId(1);
        assertEquals(1, farmer.getId());
    }

    @Test
    public void getName_nameInstantiatesCorrectly() {
        Farmer farmer = setFarmer();
        assertEquals("kev", farmer.getName());
    }

    @Test
    public void getLocation_locationInstantiatesCorrectly() {
        Farmer farmer = setFarmer();
        assertEquals("mombasa", farmer.getLocation());
    }

    @Test
    public void getEmail_emailInstantiatesCorrectly() {
        Farmer farmer = setFarmer();
        assertEquals("kev@gmail.com", farmer.getEmail());
    }

    @Test
    public void getId_isSetCorrectly() {
        Farmer farmer = setFarmer();
        farmer.setId(1);
        assertEquals(1, farmer.getId());
    }

    //helpers
    public Farmer setFarmer(){
        return new Farmer("kev", "mombasa", "kev@gmail.com");
    }
}