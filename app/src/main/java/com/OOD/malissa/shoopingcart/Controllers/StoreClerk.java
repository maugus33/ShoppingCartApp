package com.OOD.malissa.shoopingcart.Controllers;

import android.accounts.Account;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Interfaces.UserType;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.Inventory;
import com.OOD.malissa.shoopingcart.Models.Product;

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
