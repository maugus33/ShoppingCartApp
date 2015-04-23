package com.OOD.malissa.shoopingcart.Test.UnitTests;


import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.BuyerAccount;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Iterator;

public class AccountListTest extends TestCase {

    /**
     * Test used to ensure that Accountlist is initialized correctly
     * @author: Malissa Augustin
     */
    public void testInitialized() {

        AccountList li = AccountList.getInstance();

        // create list to add
        int count = 0;
        ArrayList<BuyerAccount>_buyerAccounts = new ArrayList<>();
        // create a bunch of buyers and add them to list
        for(int i= 0; i < 2; i++)
        {
            BuyerAccount buyer = new BuyerAccount("bUser"+i,"123abc"+i);

            // for every 3rd buyer...
            if(count % 3 == 0)
            {
                // give them a credit card
                // note: they all have the same expiration date
                buyer.addcCard("10001234567891"+ i,"01/12/18");
            }
            _buyerAccounts.add(buyer);
            count++;
        }

        // add buyer list
        li.initialized(_buyerAccounts,"BuyerList");

        Iterator it = li.iterator(false);

        ArrayList<BuyerAccount>_buyerAccounts2 = null;
        while(it.hasNext())
        {
            _buyerAccounts2.add((BuyerAccount) it.next());
        }

        // this should be true
        assertEquals(_buyerAccounts2, _buyerAccounts);



        ArrayList<SellerAccount>_sellerAccounts = new ArrayList<>();
        // create a bunch of buyers and add them to list
        for(int i= 0; i < 3; i++)
        {
            SellerAccount seller = new SellerAccount("sUser"+i,"s123abc"+i);

            _sellerAccounts.add(seller);

        }

        // add buyer list
        li.initialized(_sellerAccounts,"SellerList");

        Iterator it2 = li.iterator(true);

        ArrayList<SellerAccount>_sellerAccounts2 = null;
        while(it2.hasNext())
        {
            _sellerAccounts2.add((SellerAccount)it2.next());
        }


        // this should be true
        assertEquals(_sellerAccounts2,_sellerAccounts);


    }

    /**
     * Test function used to make sure that accounts added are
     * @author: Malissa Augustin
     */
    public void testAddAccount()  {
        AccountList li = AccountList.getInstance();

        BuyerAccount buyer = new BuyerAccount("bUser","123abc");
        SellerAccount seller = new SellerAccount("sUser","s123abc");

        // add a buyer account

        li.addAccount(User.BUYER,buyer);

        Iterator it = li.iterator(false);

        BuyerAccount buyer2 = (BuyerAccount)it.next();

        // assert that buyer accounts are the same
        assertEquals(buyer2,buyer);

        li.addAccount(User.SELLER,seller);

        Iterator it2 = li.iterator(true);

        SellerAccount seller2 = (SellerAccount)it2.next();

        // assert that buyer accounts are the same
        assertEquals(seller2,seller);




    }
}
