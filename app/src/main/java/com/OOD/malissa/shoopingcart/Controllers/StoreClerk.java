package com.OOD.malissa.shoopingcart.Controllers;

import android.view.View;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.*;
import com.OOD.malissa.shoopingcart.Activities.Interfaces.UserType;
import com.OOD.malissa.shoopingcart.Activities.Login;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;
import com.OOD.malissa.shoopingcart.Models.BuyerAccount;
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
    protected BuyerAccount _userAccountB; // when doing anything involving specific accounttype data, use casting
    protected SellerAccount _userAccountS;
    private AccountList _accList = new AccountList();
    protected Iterator _invenIterator;
    protected Inventory _currentInventory = new Inventory();
    //endregion

    public void initializeModel(String key){

    }

    public void updateStorage(){

    }

    /**
     * This is used to iterate through _accList to check the username
     * and password of the user.
     * @param username a String which contains the user's username
     * @param pass a String which contains the user's password
     * @param isSeller a boolean which determines whether to iterate
     *                 through the seller accounts or buyer accounts
     * @return a boolean to determine if the account was found or not.
     */
    public boolean verifyAccount(String username, String pass, boolean isSeller){

        _accList.set_isSeller(isSeller);

        for(Iterator iter = _accList.iterator(); iter.hasNext();) {

            if(isSeller){
                _userAccountS = (SellerAccount) iter.next();
                if (_userAccountS.getUsername().equals(pass) && _userAccountS.getUsername().equals(username))
                    return true;
            }
            else {

                _userAccountB = (BuyerAccount) iter.next();
                if (_userAccountB.getUsername().equals(pass) && _userAccountB.getUsername().equals(username))
                    return true;
            }

        }

        return false;
    }

    /**
     * A function that calls verifyAccount then calls the function that
     * sets up BrowseList if the account is verified.
     * @param username
     * @param pass
     * @param isSeller
     *
     */
    public void login(String username, String pass, boolean isSeller) {

        if(verifyAccount(username, pass, isSeller)) {

            if(isSeller) {
                _user = User.SELLER;

                setUser(new Seller());

            }
            else {
                _user = User.BUYER;
                setUser(new Buyer());
            }

        }

        Login.logInFail.setVisibility(View.VISIBLE);

    }

    /**
     * Used to set which user logged in and call setup function for that user
     * @param user UserType that logged in
     */
    private void setUser(UserType user){

        user.setUp(_user);
    }

    public void getProductDets(Product item){

    }

    public void updateAccount(ArrayList<String> infoListArrayList){

    }

    public void showAccountInfo(){

    }
}
