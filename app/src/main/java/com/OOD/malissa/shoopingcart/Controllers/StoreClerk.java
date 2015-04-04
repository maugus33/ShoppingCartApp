package com.OOD.malissa.shoopingcart.Controllers;

import android.accounts.Account;
import android.content.Context;


import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Interfaces.UserType;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.Inventory;
import com.OOD.malissa.shoopingcart.Models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Malissa on 3/29/2015.
 *
 */
public class StoreClerk {

    //region SINGLETON SETUP
    private static StoreClerk ourInstance = new StoreClerk();

    public static StoreClerk getInstance() {
        return ourInstance;
    }

    // protected so the children of class can use them
    protected StoreClerk() {
        _accList = AccountList.getInstance();
        _user = null;
        _userAccount = null;
        _invenIterator = null;
        _currentInventory = null;
    }
    //endregion

    //region INSTANCE VARIABLES
    protected User _user;
    protected Account _userAccount; // when doing anything involving specific accounttype data, use casting
    private AccountList _accList;
    protected Iterator _invenIterator;
    protected Inventory _currentInventory;
    //endregion

    public void initializeModel(String key){

    }

    /**
     * Initializes all models in system
     * All model info is located in the Login context
     */
    public void initializeAllModel(Context context){
        // used to store the current storage key
        String currentStorageKey = null;
        Object savedItem = null;

        try {
            //initialize buyerlist
            currentStorageKey = "BuyerList";
           savedItem =  StorageController.readObject(context,currentStorageKey);
            //todo: check to make sure if storagecontroller throws IOException if there is
            //todo: nothing in the Internal Storage. if so, add logic to use pre made stuff
            _accList.initialized(savedItem, currentStorageKey);

            //initialize sellerlist
            currentStorageKey = "SellerList";
            savedItem = StorageController.readObject(context,currentStorageKey);
            _accList.initialized(savedItem, currentStorageKey);

        } catch ( ClassCastException e) {
            System.out.println("Incorrect object cast for : " + currentStorageKey);
            e.printStackTrace();

        }  catch (IOException e) {
            System.out.println("Issue getting data from: " + currentStorageKey);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("The class is not found. Issue getting data from: " + currentStorageKey);
            e.printStackTrace();
        }
        // initialize account lists



    }

    public void updateStorage(){

    }

    public boolean verifyAccount(String username, String pass, boolean isSeller){
        return false;
    }

    public void login(String username, String pass, boolean isSeller){

    }

    /**
     * Used to set which user logged in and call setup function for that user
     * @param user UserType that logged in
     */
    private void setUser(UserType user){

    }

    public void getProductDets(Product item){

    }

    public void updateAccount(ArrayList<String> infoListArrayList){

    }

    public void showAccountInfo(){

    }


}
