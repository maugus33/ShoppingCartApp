package com.OOD.malissa.shoopingcart.Controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


import com.OOD.malissa.shoopingcart.Activities.Account;
import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Login;
import com.OOD.malissa.shoopingcart.Activities.ProductDetails;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.Interfaces.Resettable;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;
import com.OOD.malissa.shoopingcart.Models.BuyerAccount;
import com.OOD.malissa.shoopingcart.Models.Inventory;
import com.OOD.malissa.shoopingcart.Models.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class StoreClerk implements Resettable{


    //region INSTANCE VARIABLES
    protected  User _user;
    protected  BuyerAccount _userAccountB; // when doing anything involving specific accounttype data, use casting. static so buyer/seller clerk can have access
    protected  SellerAccount _userAccountS;
    protected AccountList _accList;
    protected Inventory _currentInventory;
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
        this._currentInventory = null;
    }
    //endregion

    /**
     * Used to reset the current clerk
     * @author Malissa Augustin
     */
    @Override
    public void reset() {

        //if there is a reference to the accountlist and it has not been reset yet...
        if(this._accList != null && !this._accList.isReset())
        {
            //reset it.
            this._accList.reset();
        }

        this._user = null;
        this._userAccountB = null;
        this._userAccountS = null;
        this._currentInventory = null;
    }

    /**
     * An accessor to obtain _userAccountS.
     * @return a SellerAccount that is the current seller account or null if not initialized
     * @author Malissa Augustin
     */
    public SellerAccount get_userAccountS(){ return this._userAccountS;}

    /**
     * An accessor to obtain _userAccountB.
     * @return a BuyerAccount that is the current buyer account or null if not initialized
     * @author Paul Benedict Reyes
     */
    public BuyerAccount get_userAccountB(){ return this._userAccountB;}

    /**
     * Initializes all models in system
     * All model info is located in the Login context
     * @param context a Context of the activity active when this is called
     * @author Malissa Augustin
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
     * @author Malissa Augustin
     */
    public void updateStorage(String identifier){

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
     * @author Paul Benedict Reyes
     */
    public boolean verifyAccount(String username, String pass, boolean isSeller){
        // set the account list isSeller status so that the list knows which list to look at
        _accList.set_isLookingForSeller(isSeller);

        for(Iterator iter = _accList.iterator(); iter.hasNext();) {
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
     * @param username a String that is the username used to log in
     * @param pass a String that is the password used to log in
     * @param isSeller a boolean that determines if the  user is a seller
     * @author Paul Benedict Reyes
     *
     */
    public void login(String username, String pass, boolean isSeller) {

        if(verifyAccount(username, pass, isSeller)) {

            //Login.logInFail.setVisibility(View.GONE);
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
            // post toast
            Toast.makeText(Login.getAppContext(), "Invalid username or password.",
                    Toast.LENGTH_LONG).show();
            //Login.logInFail.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Used to set which user logged in and call setup function for that user
     * removed use of the usertype.
     * @author Malissa Augustin
     */
    private void setUser(){
        Intent i = new Intent(Login.getAppContext(), BrowseList.class);
        i.putExtra("User", this._user);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Login.getAppContext().startActivity(i);
    }


    /**
     * An accessor used to obtain the _user User enum.
     * @return a User enum that is the value of _user
     * @author Malissa Augustin
     */
    public User currentUserType() { return this._user;}

    /**
     * Function used to get the productDetails of something
     * @param item the Product whose details will be displayed
     *             @author Malissa Augustin
     */
    public void getProductDets(Product item){

        Intent i = new Intent(BrowseList.getAppContext(), ProductDetails.class);
        // grab the user type
        i.putExtra("User", this._user);
        // grab the product information
        i.putExtra("Product", item.toArrayList());
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        goToActivity(BrowseList.getAppContext(),i);
    }

    /**
     * Updates the current account with the information.
     * @param infoListArrayList the new account information in an ArrayList of String
     * @param userType a User enum that determines whether the account is a buyer or seller
     * @author Paul Benedict Reyes
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
     * Added this to be able to check the username's uniqueness.
     * @param username a String which will be compared with the existing usernames.
     * @param userType a User enum that determines whether the username is for buyer or seller
     * @return a boolean that determines uniqueness, true = unique, false = used.
     * @author Paul Benedict Reyes
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
     * @param context the Context of the screen that calls the Account activity
     * @param userType a User enum that determines whether the account is a buyer or seller
     * @author Paul Benedict Reyes
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

    /**
     * Used to go to an activity.
     * @param from the Context of the activity that wishes to create a new activity
     * @param i the Intent that contains the new activity to be created
     * @author Malissa Augustin
     */
    public void goToActivity(Context from, Intent i)
    {
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       from.startActivity(i);

    }

    /**
     * A method called when registering as a new user.
     * @param usernameString a String that has the user's username
     * @param passwordString a String that has the user's password
     * @param isSeller a boolean that determines if the account will be saved as
     *                 a seller or a buyer
     * @author Malissa Augustin
     */
    public void register(String usernameString, String passwordString, boolean isSeller) {
        User user;
        if(isSeller)
            user = User.SELLER;
        else
            user = User.BUYER;

        //reference for regex: https://msdn.microsoft.com/en-us/library/az24scfc%28v=vs.110%29.aspx
        //http://math.hws.edu/eck/cs229/regular_expressions.html
        // username must not contain spaces or non-wordcharacters
        if(usernameString == null|| passwordString == null || !usernameString.matches("\\b\\w+\\b") || !passwordString.matches("\\b\\w+\\b"))
        {
            Toast.makeText(Login.getAppContext(), "Please fill in Username and password with correct values",
                    Toast.LENGTH_LONG).show();
        }
        // check to see if the username is taken. true = unique
       else if( checkUsername(usernameString, user) )
       {
           //create the user and add it to the list and save it to memory
           if(isSeller)
           {
               SellerAccount account = new SellerAccount(usernameString,passwordString);
               this._accList.addAccount(user,account);
               this.updateStorage("SellerList");

           }
           else
           {
               BuyerAccount account = new BuyerAccount(usernameString,passwordString);
               this._accList.addAccount(user,account);
               this.updateStorage("BuyerList");
           }

           // now set this account as the current object and login
           login(usernameString,passwordString,isSeller);

       }
        else
       {
           // post toast
           Toast.makeText(Login.getAppContext(), "Username taken.",
                   Toast.LENGTH_LONG).show();
       }
    }
}
