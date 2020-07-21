package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
    @Test
    public void newCustomer_instantiatesCorrectly() {
        Customer customer = setCustomer();
        assertTrue(customer instanceof Customer);
    }
    @Test
    public void testEquals_customerWithSameNameLocationEmailAreEqual() {
        Customer customer = setCustomer();
        Customer otherCustomer = setCustomer();
        assertTrue(customer.equals(otherCustomer));
    }

    @Test
    public void testHashCode_customerWithSameNameLocationEmailAreEqual() {
        Customer customer = setCustomer();
        Customer otherCustomer = setCustomer();
        assertTrue(customer.equals(otherCustomer));
    }

    @Test
    public void setId_isSetCorrectly() {
        Customer customer = setCustomer();
        customer.setId(1);
        assertEquals(1, customer.getId());
    }

    @Test
    public void getName_nameInstantiatesCorrectly() {
        Customer customer = setCustomer();
        assertEquals("kev", customer.getName());
    }

    @Test
    public void getLocation_locationInstantiatesCorrectly() {
        Customer customer = setCustomer();
        assertEquals("mombasa", customer.getLocation());
    }

    @Test
    public void getEmail_emailInstantiatesCorrectly() {
        Customer customer = setCustomer();
        assertEquals("kev@gmail.com", customer.getEmail());
    }

    @Test
    public void getId_isSetCorrectly() {
        Customer customer = setCustomer();
        customer.setId(1);
        assertEquals(1, customer.getId());
    }

    //helpers
    public Customer setCustomer(){
        return new Customer("kev", "mombasa", "kev@gmail.com");
    }
}