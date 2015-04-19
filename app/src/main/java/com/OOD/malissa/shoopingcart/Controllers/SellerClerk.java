package com.OOD.malissa.shoopingcart.Controllers;


import android.content.Context;
import android.content.Intent;

import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Login;
import com.OOD.malissa.shoopingcart.Models.BuyerAccount;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

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

    protected void setUpUser(User user, SellerAccount seller)
    {
        super._user = user;
        super._userAccountS = seller;
    }
    public void removeProduct(ArrayList<String> infoList){

        boolean foundProduct = false;
        // make a product from the given information
        Product newItem = new Product(infoList.get(0),
                infoList.get(1),
                infoList.get(2),
                infoList.get(3),
                Integer.parseInt(infoList.get(4)),
                Double.parseDouble(infoList.get(5)),
                Double.parseDouble(infoList.get(6)),
                infoList.get(7)
        );
        //compare product with items in inventory to see if it is the same
        if(_inventoryIter == null)
            _inventoryIter = super._userAccountS.get_InventoryIterator();
        // while you haven't reached the end...
        while(_inventoryIter.hasNext() && foundProduct == false)
        {
            //get the next product
            Product item = (Product) _inventoryIter.next();
            // and see if it's equal in terms of id and seller id
            if(item.equals(newItem))
            {
                // if so, remove item from inventory
                super._userAccountS.removeItem(item);
                foundProduct = true;

                // and then save the account into memory
                this.updateStorage("SellerList");
            }

        }
        if(foundProduct == false)
        {
            Logger log = Logger.getAnonymousLogger();
            log.warning("seller Product is not found in seller inventory ");
        }

        // reset inventory interator
        _inventoryIter = null;



    }

    public void addProduct(){

    }

    public void getFinance(){

    }

    /**
     * Function used to save updates to products
     * @param infoList
     */
    public void saveProductChanges(ArrayList<String> infoList){

        boolean foundProduct = false;
        // make a product from the given information
        Product newItem = new Product(infoList.get(0),
                infoList.get(1),
                infoList.get(2),
                infoList.get(3),
                Integer.parseInt(infoList.get(4)),
                Double.parseDouble(infoList.get(5)),
                Double.parseDouble(infoList.get(6)),
                infoList.get(7)
        );
        //compare product with items in inventory to see if it is the same
        if(_inventoryIter == null)
            _inventoryIter = super._userAccountS.get_InventoryIterator();
        // while you haven't reached the end...
        while(_inventoryIter.hasNext() && foundProduct == false)
        {
            //get the next product
            Product item = (Product) _inventoryIter.next();
            // and see if it's equal in terms of id and seller id
            if(item.equals(newItem))
            {
                // if so, update information
                item.copyData(newItem);
                foundProduct = true;

                // and then save the account into memory
                this.updateStorage("SellerList");
            }

        }
        if(foundProduct == false)
        {
           Logger log = Logger.getAnonymousLogger();
            log.warning("seller Product is not found in seller inventory ");
        }

        // reset inventory interator
        _inventoryIter = null;

    }

    public void returnToBrowseList(Context context)
    {
        Intent i = new Intent(context, BrowseList.class);
        i.putExtra("User", this._user);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
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
           //_inventoryIter = StoreClerk.getInstance().get_userAccountS().get_InventoryIterator();
            _inventoryIter = super._userAccountS.get_InventoryIterator();
        //return a product until you reach the end
        try {
            return (Product) _inventoryIter.next();
        }
        catch(NoSuchElementException e)// no more seller accounts
        {
            // reset inventory interator
            _inventoryIter = null;
            return null;
        }

    }


}
