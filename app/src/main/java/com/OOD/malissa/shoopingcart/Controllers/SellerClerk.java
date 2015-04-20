package com.OOD.malissa.shoopingcart.Controllers;


import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.OOD.malissa.shoopingcart.Activities.AddProduct;
import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.FinancialDialog;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.removeProductDialog;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

/**
 * Created by Malissa on 3/29/2015.
 */
public class SellerClerk extends StoreClerk {
    //region INSTANCE VARIABLES
    private NewIterator _inventoryIter;
    private DecimalFormat df = new DecimalFormat("#.00");
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

    // function used to call add product activity
    public void addProduct(){

        Intent i = new Intent(BrowseList.getAppContext(), AddProduct.class);
        super.goToActivity(BrowseList.getAppContext(),i);

    }

    // function used to save a new product to the seller's inventory
    public void saveNewProduct(ArrayList<String> infoList){
        String sellerID = super._userAccountS.get_sellerID();

        //build a new product
        //infoList organization: 0 - name, 1 - description, 2 - type, 3 - quantity, 4 - invoiceP, 5 - sellingP
        String newProductID = super._userAccountS.calculateProductID();
        Product newItem = new Product(infoList.get(0),
                newProductID,
                infoList.get(1),
                infoList.get(2),
                Integer.parseInt(infoList.get(3)),
                Double.parseDouble(infoList.get(4)),
                Double.parseDouble(infoList.get(5)),
                sellerID
        );

        //add the new product to the inventory
        super._userAccountS.addProduct(newItem);

        // and then save the account into memory
        this.updateStorage("SellerList");

        // then go back to the browse list
        Intent i = new Intent(AddProduct.getAppContext(), BrowseList.class);
        i.putExtra("User", this._user);
        super.goToActivity(AddProduct.getAppContext(),i);

    }

    public DialogFragment getRemoveProductDialog(ArrayList<String> productInfo){
        DialogFragment dialog = new removeProductDialog();
        Bundle args = new Bundle();
        args.putStringArrayList("product",productInfo);
        args.putString("title", "Remove Product");
        args.putString("message", "Are you sure you want to remove this product from your inventory?");
        dialog.setArguments(args);
        return dialog;

    }


    public DialogFragment getFinanceDialog(){
        DialogFragment dialog = new FinancialDialog();
        Bundle args = new Bundle();
        args.putString("title", "Financial Summary");
        args.putString("message", this._userAccountS.getFinances());
        dialog.setArguments(args);
        return dialog;

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
                //update cost information
                this._userAccountS.update(0.0,(newItem.get_invoiceP()*newItem.get_quantity())
                                                    - (item.get_invoiceP()*item.get_quantity()));

                // update product information
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

    // let's not use this. use the gotoActivity funciton in StoreClerk
    public void returnToBrowseList(Context context)
    {
        Intent i = new Intent(context, BrowseList.class);
        i.putExtra("User", this._user);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
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
