package com.OOD.malissa.shoopingcart.Test.UnitTests;

import com.OOD.malissa.shoopingcart.Models.Inventory;
import com.OOD.malissa.shoopingcart.Models.Product;

import junit.framework.TestCase;

import java.util.Iterator;

public class InventoryTest extends TestCase {


    /**
     * Test used to ensure that items are added to the inventory correctly
     * @author: Malissa Augustin
     */
    public void testAddItem() {

        Inventory c = new Inventory();
        Product d = new Product("Hammer", "1", "\"It hits things\"", "Tools", 5, 3.00, 5.00, "1000");
        Product a = new Product("Soap", "3", "\"It washes things\"", "Hygiene", 30, 2.00, 6.00, "1000");
        c.addItem(d);
        c.addItem(a);


        Iterator i = c.iterator();

        Product e = null;
        Product z = null;

        // grab products
        if(i.hasNext())
            e = (Product) i.next();
        if(i.hasNext())
            z = (Product) i.next();

        if(i.hasNext())
            System.out.println("inventory has more products then it should");




        // check to see if the item is the same: it should be true
        assertEquals(d,e);
        // check to see if the item is the same: it should be false
        assertEquals(d,z);
    }

    /**
     * Test used to ensure that the item is remove correctly from the inventory
     * @author: Malissa Augustin
     */
    public void testRemoveItem()  {
        Inventory c = new Inventory();
        Product d = new Product("Hammer", "1", "\"It hits things\"", "Tools", 5, 3.00, 5.00, "1000");
        Product a = new Product("Soap", "3", "\"It washes things\"", "Hygiene", 30, 2.00, 6.00, "1000");
        c.addItem(d);
        c.addItem(d);
        c.addItem(d);
        //second item
        c.addItem(a);

        //remove the first
        c.removeItem(d);

        Iterator i = c.iterator();

        Product e = null;

        // grab products
        if(i.hasNext())
            e = (Product) i.next();


        // ensure the first item is a now and not d

        // should fail
        assertEquals(d,e);
        // should pass as a is now the first product
        assertEquals(a,e);


    }
    /**
     * Function used to test and make sure that each product id is different
     * @author: Malissa Augustin
     *
     */
    public void testGetNewProductID()  {

        Inventory items = new Inventory();
        Product d = new Product("Hammer", "1", "\"It hits things\"", "Tools", 5, 3.00, 5.00, "1000");
        Product a = new Product("Soap", "3", "\"It washes things\"", "Hygiene", 30, 2.00, 6.00, "1000");
        items.addItem(d);
        items.addItem(a);

        // create a new productId
        String id1 = items.getNewProductID("1000");

        // ensure it is different than any of the added products
        assertFalse(d.get_ID() == id1);
        assertFalse(a.get_ID() == id1);

        String id2 = items.getNewProductID("1000");

        // ensure that the second id is different than the first id
        assertFalse(id2 == id1);


    }



}