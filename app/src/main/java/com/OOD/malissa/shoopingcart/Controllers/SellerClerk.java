package com.OOD.malissa.shoopingcart.Controllers;


import com.OOD.malissa.shoopingcart.Models.BuyerAccount;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;

import java.util.NoSuchElementException;

/**
 * Created by Malissa on 3/29/2015.
 */
public class SellerClerk extends StoreClerk {
    //region INSTANCE VARIABLES
    private NewIterator _inventoryIter;
    //endregion
    //region SINGLETON SETUP
    private static SellerClerk ourInstance = new SellerClerk();

    public static SellerClerk getInstance() {
        return ourInstance;
    }

    private SellerClerk() {
        super();
    }
    //endregion

    public void removeProduct(Product item){

    }

    public void addProduct(){

    }

    public void getFinance(){

    }

    public void saveProductChanges(Product item){

    }

    /**
     * used to assign id to new products
     */
    private String assignID(){
        return null;
    }

    /**
     * Gets the current seller's inventory products one at a time
     * @return the next product in the seller's inventory or null if there is no more
     */
    public Product getInventoryProd(){
        // get the invetory iterator
        if(_inventoryIter == null)
            // used accessor to get seller info from storeClerk
           _inventoryIter = StoreClerk.getInstance().get_userAccountS().get_InventoryIterator();
        //return a product until you reach the end
        try {
            return (Product) _inventoryIter.next();
        }
        catch(NoSuchElementException e)// no more seller accounts
        {
            return null;
        }

    }


}
