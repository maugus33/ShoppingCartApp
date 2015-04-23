package com.OOD.malissa.shoopingcart.Test.IntergrationTests;

/**
 * Created by Malissa on 4/22/2015.
 */
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.Models.AccountList;

import junit.framework.TestCase;

public class StoreClerkTest extends TestCase {


    /**
     * Test used to ensure verify account actually finds accounts that are in the list
     * @author: Malissa Augustin
     */
    public void testVerifyAccount()  {

        AccountList li = AccountList.getInstance();
        StoreClerk clerk = StoreClerk.getInstance();

        // add preset buyer list
        li.initialized(null,"BuyerList");
        //add preset seller list
        li.initialized(null,"SellerList");

        //try to find a buyer account that exists. should be true
        boolean isThere = clerk.verifyAccount("bUser0","123abc0",false);
        assertTrue(isThere);
        //try to find a buyer account that doesn't exists. should be false
        isThere = clerk.verifyAccount("bUser00","123abc00",false);
        assertTrue(isThere);



        //try to find a buyer account that exists. should be true
        boolean isThere2 = clerk.verifyAccount("sUser0","s123abc0",true);
        assertTrue(isThere2);
        //try to find a buyer account that doesn't exists. should be false
        isThere2 = clerk.verifyAccount("sUser00","s123abc00",true);
        assertTrue(isThere2);
    }


}
