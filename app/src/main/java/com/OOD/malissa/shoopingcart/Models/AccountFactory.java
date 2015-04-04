package com.OOD.malissa.shoopingcart.Models;

/**
 * Created by Malissa on 3/30/2015.
 */
public class AccountFactory {
    // Paul: Should we even have this? It's not really acting like a Factory tbh...
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
