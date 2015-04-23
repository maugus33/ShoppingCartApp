package com.OOD.malissa.shoopingcart.Test.UnitTests;

import com.OOD.malissa.shoopingcart.Models.Cart;
import com.OOD.malissa.shoopingcart.Models.Product;

import junit.framework.TestCase;

public class CartTest extends TestCase {

    /**
     * Unit test to test if an item is added to the cart properly. it should only add new items and
     * just update the count of items it already has in the cart
     * @author: Malissa Augustin
     */
    public void testAddItem(){

        Cart c = new Cart();
        Product d = new Product("Hammer", "1", "\"It hits things\"", "Tools", 5, 3.00, 5.00, "1000");
        Product a = new Product("Soap", "3", "\"It washes things\"", "Hygiene", 30, 2.00, 6.00, "1000");
        c.addItem(d);
        c.addItem(a);

        // get this item ( function is a simple getter
       Product e = c.getCartItems(0);
       Product z = c.getCartItems(1);

        // check to see if the item is the same: it should be true
        assertEquals(d,e);
        // check to see if the item is the same: it should be false
        assertEquals(d,z);

        //check to make sure that when a product is added, that only the count should go up. a new product should not be
        //added
        c.addItem(d);

        int num = c.itemCount(d);

        // check to see if the number of product d in cart is 2
        assertEquals(num,2);
    }

    /**
     * Unit testing to ensure that items are removed from the cart properly
     * @author: Malissa Augustin
     */
    public void testRemoveItem()  {

        Cart c = new Cart();
        Product d = new Product("Hammer", "1", "\"It hits things\"", "Tools", 5, 3.00, 5.00, "1000");
        Product a = new Product("Soap", "3", "\"It washes things\"", "Hygiene", 30, 2.00, 6.00, "1000");
        c.addItem(d);
        c.addItem(d);
        c.addItem(d);
        //second item
        c.addItem(a);

        //remove the first
        c.removeItem(d);

        // ensure the first item is a now and not d
        Product e = c.getCartItems(0);
        // should fail
        assertEquals(d,e);
        // should pass as a is now the first product
        assertEquals(a,e);

        // ensure that the item count of d is removed: should throw an out if bounds exception
        try {
            int num1 = c.itemCount(d);
            assertFalse(num1 == 3);// shouldn't reach this line
        }catch(IndexOutOfBoundsException x)
        {
            System.out.println("out of bounds, item doesn't exist");
        }


    }

    /**
     * Test used to ensure that the correct count of the cart is given
     * @author: Malissa Augustin
     */
    public void testGetCount() {

        Cart c = new Cart();
        Product d = new Product("Hammer", "1", "\"It hits things\"", "Tools", 5, 3.00, 5.00, "1000");
        Product a = new Product("Soap", "3", "\"It washes things\"", "Hygiene", 30, 2.00, 6.00, "1000");
        c.addItem(d);
        c.addItem(d);
        c.addItem(d);
        //second item
        c.addItem(a);
        c.addItem(a);

        int firstCount = c.getCount();

        // confirm that the count is 5
        assertTrue(firstCount == 5);

        //add more items
        c.addItem(d);
        c.addItem(d);


        int secondCount = c.getCount();

        // confirm that the count is now 7
        assertTrue(secondCount == 7);

    }


}