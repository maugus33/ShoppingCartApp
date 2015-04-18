package com.OOD.malissa.shoopingcart.Models;

/**
 * Created by Malissa on 3/30/2015.
 */
public class AccountFactory {
    // No real reason to have this. will need to delete
    public BuyerAccount getBuyerAccount(String username, String pass )
    {
        BuyerAccount account = new BuyerAccount(username,pass);

        return account;
    }

    public SellerAccount getSellerAccount(String username, String pass)
    {
        SellerAccount account = new SellerAccount(username,pass);
        return account;
    }
}
