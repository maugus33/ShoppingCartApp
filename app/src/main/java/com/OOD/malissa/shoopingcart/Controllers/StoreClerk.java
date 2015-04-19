package com.OOD.malissa.shoopingcart.Controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import com.OOD.malissa.shoopingcart.Activities.Account;
import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.Buyer;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.Seller;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Interfaces.UserType;
import com.OOD.malissa.shoopingcart.Activities.Login;
import com.OOD.malissa.shoopingcart.Activities.ProductDetails;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;
import com.OOD.malissa.shoopingcart.Models.BuyerAccount;
import com.OOD.malissa.shoopingcart.Models.Inventory;
import com.OOD.malissa.shoopingcart.Models.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Malissa on 3/29/2015.
 *
 */
public class StoreClerk {


    //region INSTANCE VARIABLES
    protected  User _user;
    protected  BuyerAccount _userAccountB; // when doing anything involving specific accounttype data, use casting. static so buyer/seller clerk can have access
    protected  SellerAccount _userAccountS;
    protected AccountList _accList;
    //protected NewIterator _sellerIterator; // might not use??
    protected Inventory _currentInventory; // MIGHT NOT USE?
    //endregion

    //region SINGLETON SETUP
    private static StoreClerk ourInstance = new StoreClerk();

    public static StoreClerk getInstance() {
        return ourInstance;
    }

    // protected so the children of class can use them
    protected StoreClerk() {
        this._accList = AccountList.getInstance();
        this._user = null;
        this._userAccountB = null;
        this._userAccountS = null;
        //this._sellerIterator = null;
        this._currentInventory = null;
    }
    //endregion

    public SellerAccount get_userAccountS(){ return this._userAccountS;}

    //Added accessor for buyer useraccount. 4/19/15
    public BuyerAccount get_userAccountB(){ return this._userAccountB;}

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
        String baseFolder = context.getFilesDir().getAbsolutePath();
        File file;
        try {
            //initialize buyerlist
            currentStorageKey = "BuyerList";

            file = new File(baseFolder + File.separator + currentStorageKey + ".ser");

            if (!file.exists())
                throw new IOException();
                savedItem = StorageController.readObject(context, file);

        } catch ( ClassCastException e) {
            System.out.println("Incorrect object cast for : " + currentStorageKey);
            e.printStackTrace();

        }  catch (IOException e) {
            System.out.println("Issue getting data from: " + currentStorageKey);
            e.printStackTrace();
            savedItem = null;


        } catch (ClassNotFoundException e) {
            System.out.println("The class is not found. Issue getting data from: " + currentStorageKey);
            e.printStackTrace();
        }
        //initialize buyerlist
        _accList.initialized(savedItem, currentStorageKey);


            //initialize sellerlist
          try{
            currentStorageKey = "SellerList";
            baseFolder = context.getFilesDir().getAbsolutePath();
            file = new File(baseFolder + File.separator + currentStorageKey + ".ser");

            if (!file.exists())
                throw new IOException();
            savedItem = StorageController.readObject(context,file);


        } catch ( ClassCastException e) {
            System.out.println("Incorrect object cast for : " + currentStorageKey);
            e.printStackTrace();

        }  catch (IOException e) {
            System.out.println("Issue getting data from: " + currentStorageKey);
            e.printStackTrace();
              savedItem = null;


        } catch (ClassNotFoundException e) {
            System.out.println("The class is not found. Issue getting data from: " + currentStorageKey);
            e.printStackTrace();
        }
        //initialize sellerlist
        _accList.initialized(savedItem, currentStorageKey);



    }

    /**
     * Used to save objects to internal storage
     * @param identifier identifies what kind of object is being saved. used as the key
     */
    public void updateStorage(String identifier){

        // todo: use enums to identify the different keys

        // call accountList to save the proper item
        //storeclerk decides that saving the info in the login context
        if(identifier.equals("BuyerList") || identifier.equals("SellerList")) {
            String baseFolder = Login.getAppContext().getFilesDir().getAbsolutePath();
            File file = new File(baseFolder  + File.separator + identifier + ".ser");

            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                _accList.save(identifier,file, Login.getAppContext());

        }


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
        // set the account list isSeller status so that the list knows which list to look at
        _accList.set_isLookingForSeller(isSeller);

        for(Iterator iter = _accList.iterator(); iter.hasNext();) {
            // todo: reset the iterator once the user account is found. might need to add new/modify first function for iterator
            if(isSeller){
                this._userAccountS = (SellerAccount) iter.next();
                if (this._userAccountS.getPassword().equals(pass) && this._userAccountS.getUsername().equals(username))
                    {return true;}
                //_userAccountS = null;
            }
            else {

                this._userAccountB = (BuyerAccount) iter.next();
                if (this._userAccountB.getPassword().equals(pass) && this._userAccountB.getUsername().equals(username))
                    return true;
                //_userAccountB = null;
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

            Login.logInFail.setVisibility(View.GONE);
            if(isSeller) {
                _user = User.SELLER;
                //forward info to sellerClerk
                SellerClerk.getInstance().setUpUser(this._user,this._userAccountS);
            }
            else {
                _user = User.BUYER;
                //forward info to sellerClerk
                BuyerClerk.getInstance().setUpUser(this._user,this._userAccountB);
            }

            // set up the correct user for the browselist
            setUser();

        }
        else
        {
            //todo: make this a dialog box
            Login.logInFail.setVisibility(View.VISIBLE);
        }




    }

    /**
     * Used to set which user logged in and call setup function for that user
     * removed use of the usertype
     */
    private void setUser(){
    //private void setUser(UserType user){

       // user.setUp(_user);

        Intent i = new Intent(Login.getAppContext(), BrowseList.class);
        i.putExtra("User", this._user);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Login.getAppContext().startActivity(i);
    }



    public User currentUserType() { return this._user;}

    /**
     * Function used to get the productDetails of something
     * @param item
     */
    public void getProductDets(Product item){

        Intent i = new Intent(BrowseList.getAppContext(), ProductDetails.class);
        // grab the user type
        i.putExtra("User", this._user);
        // grab the product information
        i.putExtra("Product", item.toArrayList());
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BrowseList.getAppContext().startActivity(i);


    }

    /**
     * Updates the current account with the information.
     * 4/19/15
     * @param infoListArrayList
     */
    public void updateAccount(ArrayList<String> infoListArrayList, User userType){
        if(userType == User.BUYER){
            this._userAccountB.setUsername(infoListArrayList.get(0));
            this._userAccountB.setPassword(infoListArrayList.get(1));
            updateStorage("BuyerList");
        }
        if(userType == User.SELLER){
            this._userAccountS.setUsername(infoListArrayList.get(0));
            this._userAccountS.setPassword(infoListArrayList.get(1));
            updateStorage("SellerList");
        }

    }

    /**
     * Added this to be able to check the username's uniqueness. 4/19/15
     * @param username a String which will be comapred with the existing usernames.
     * @param userType a User enum that determines whether the username is for buyer or seller
     * @return a boolean that determines uniqueness, true = unique, false = used.
     */
    public boolean checkUsername(String username, User userType) {
        boolean isSeller;

        if(userType == User.BUYER){
            isSeller = false;
        }
        else{
            isSeller = true;
        }

        _accList.set_isLookingForSeller(isSeller);

        for(Iterator iter = _accList.iterator(); iter.hasNext();) {
            if(isSeller){
                if(username.equals(((SellerAccount) iter.next()).getUsername()))
                    return false;
            }
            else {
                if(username.equals(((BuyerAccount) iter.next()).getUsername()))
                    return false;
            }
        }
        return true;
    }

    /**
     * Calls the account screen where account information changes can be made.
     * 4/19/15
     * @param context
     * @param userType
     */
    public void showAccountInfo(Context context, User userType){
        Intent i = new Intent(context, Account.class);
        // grab the user type
        i.putExtra("User", this._user);
        // grab the product information
        if (userType == User.BUYER) {
            i.putExtra("Account", _userAccountB.toArrayList());
        }
        if (userType == User.SELLER) {
            i.putExtra("Account",_userAccountS.toArrayList());
        }
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BrowseList.getAppContext().startActivity(i);
    }


}
