package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void newProduct_instantiatesCorrectly(){
        Product product = setProduct();
        assertTrue(product instanceof  Product);
    }

    @Test
    public void setId_idIsSetCorrectly() {
        Product product = setProduct();
        product.setId(1);
        assertEquals(1, product.getId());
    }

    @Test
    public void testEquals_productsWithEqualNameAreSame() {
        Product product = setProduct();
        Product product1 = setProduct();
        assertEquals(true, product.equals(product1));
    }

    @Test
    public void testHashCode_productsWithEqualNameAreSame() {
        Product product = setProduct();
        Product product1 = setProduct();
        assertEquals(true, product.equals(product1));
    }

    @Test
    public void getId_idInstantiatesCorrectly() {
        Product product = setProduct();
        product.setId(1);
        assertEquals(1, product.getId());
    }

    @Test
    public void getName_nameInstantiatesCorrectly() {
        Product product = setProduct();
        assertEquals(1, product.getName());
    }

    //helpers
    public Product setProduct() {
        return new Product("mangoes");
    }
}