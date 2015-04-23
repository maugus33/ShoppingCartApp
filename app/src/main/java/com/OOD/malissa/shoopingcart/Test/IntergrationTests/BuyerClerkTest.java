package com.OOD.malissa.shoopingcart.Test.IntergrationTests;

/**
 * Created by Malissa on 4/22/2015.
 */
import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Iterator;

public class BuyerClerkTest extends TestCase {

    /**
     * Test used to ensure that the function gets a product from the inventory of each seller
     * and returns it in the correct order
     * @author: Malissa Augustin
     */
    public void testGetAStoreProd() {
        AccountList li = AccountList.getInstance();
        BuyerClerk clerk = BuyerClerk.getInstance();

        //add preset seller list, 3 sellers with a total of 9 items
        li.initialized(null,"SellerList");

        Iterator it2 = li.iterator(true);

        ArrayList<SellerAccount>_sellerAccounts2 = null;
        while(it2.hasNext())
        {
            _sellerAccounts2.add((SellerAccount)it2.next());
        }
        String seller1 = _sellerAccounts2.get(0).get_sellerID();
        String seller2 = _sellerAccounts2.get(1).get_sellerID();
        String seller3 = _sellerAccounts2.get(2).get_sellerID();

        ArrayList<Product> expected = new ArrayList<>();

        // products from prepopulated list
        expected.add(new Product("Hammer", "1", "\"It hits things\"", "Tools", 5, 3.00, 5.00, seller1));
        expected.add(new Product("Nail", "2", "\"It holds things together\"", "Tools", 3, 1.00, 2.00, seller1));
        expected.add(new Product("Soap", "3", "\"It washes things\"", "Hygiene", 30, 2.00, 6.00, seller1));
        expected.add(new Product("Picture", "4", "\"It looks nice\"", "Decor", 15, 10.00, 15.00, seller2));
        expected.add(new Product("TextBook", "5", "\"It Makes you smarter\"", "Books", 20, 20.00, 50.00, seller2));
        expected.add(new Product("Cookie", "6", "\"It's a treat\"", "Food", 40, 0.50, 2.00, seller2));
        expected.add(new Product("Egg", "7", "\"Comes from chickens\"", "Food", 55, 0.20, 0.70, seller3));
        expected.add(new Product("Plastic Gnome", "8", "\"For your lawn\"", "Decor", 5, 3.00, 5.00, seller3));
        expected.add(new Product("Pillow", "9", "\"For your head\"", "Bedding", 53, 3.00, 5.00, seller3));

        ArrayList<Product> actual = new ArrayList<>();
        int i = 0;
        while(i < 10)
        {
            actual.add(clerk.getAStoreProd());
            i++;
        }
        int count = 0;
        for(Product item : expected)
        {
            // should return true for each one. product overrides equals to compare the sellerID, and productID
            assertTrue(item.equals(actual.get(count)));
            count++;
        }


    }
}
