package com.OOD.malissa.shoopingcart.Models;

import android.content.Context;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Controllers.StorageController;
import com.OOD.malissa.shoopingcart.Models.Interfaces.Initializable;
import com.OOD.malissa.shoopingcart.Models.Interfaces.Resettable;
import com.OOD.malissa.shoopingcart.Models.Interfaces.Saveable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Malissa on 3/29/2015.
 */
public class AccountList implements Iterable, Initializable,Saveable,Resettable {

    //region SINGLETON SETUP
    private static AccountList ourInstance = new AccountList();

    public static AccountList getInstance() {
        return ourInstance;
    }


    private AccountList() {
        _buyerAccounts = null;
        _sellerAccounts = null;
        _isLookingForSeller = false;
        _isreset = false;
    }
    //endregion

    // constant variable for account creation
    final private int MAX_BACCOUNT_NUM =  10;
    final private int MAX_SACCOUNT_NUM =  3;

    //region INSTANCE VARIABLES
    ArrayList<BuyerAccount> _buyerAccounts;
    ArrayList<SellerAccount> _sellerAccounts;
    boolean _isLookingForSeller;
    boolean _isreset; // bool to see if the object has been reset or not
    //endregion

    /**
     * Resets the AccountList so there are no values when logged out.
     */
    @Override
    public void reset() {
        _buyerAccounts = null;
        _sellerAccounts = null;
        _isLookingForSeller = false;
        _isreset = true;

    }

    /**
     * Creates an iterator for the AccountList.
     * @return a Iterator for the AccountList
     */
    @Override
    public Iterator iterator() {
        return new AccountListIterator();
    }

    /**
     * Iterator specifically for this class.
     * @param isSeller a boolean that determines if the iterator is for SellerAccount
     * @return an Iterator for SellerAccounts
     */
    public Iterator iterator(boolean isSeller) {
        _isLookingForSeller = isSeller;
        return new AccountListIterator();
    }

    /**
     * Used to initialize list with data saved in Internal Storage
     * If there is nothing there, then it uses premade stuff
     * @param object the object taken from storage
     * @param key the key to identify what type of object
     */
    @Override
    public void initialized(Object object, String key) {
        // is being initialized so we can set the reset flag to false
        _isreset = false;

        // if the object is null...
        if(object == null)
        {

            // there is nothing in storage. grab from premade data
            createPremadeAccounts(key);

        }
        else
        {
            // if the object is a buyer...
            if(key.equals("BuyerList"))
            {
                this._buyerAccounts = (ArrayList<BuyerAccount>) object;
            }
            else if (key.equals("SellerList"))// if it's a seller...
            {
                this._sellerAccounts = (ArrayList<SellerAccount>) object;
            }
            else
            {
               System.out.println("Invalid key used.") ;
            }

        }
    }

    /**
     * A method that adds a new account to the AccountList.
     * @param user a User enum that determines whether the new Account is a Buyer or Seller
     * @param account the new account to be added to the AccountList
     */
    public void addAccount(User user, Account account)
    {
        // add account to the list
        if(user == User.BUYER)
        {
            this._buyerAccounts.add((BuyerAccount) account);
        }
        else if(user == User.SELLER)
        {
            this._sellerAccounts.add((SellerAccount) account);
        }
    }

    @Override
    public void save(String key,File file,Context context) {

        if(key.equals("BuyerList")) {
            try {
                StorageController.writeObject(context, file, this._buyerAccounts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(key.equals(("SellerList")))
        {
            try {
                StorageController.writeObject(context, file, this._sellerAccounts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function which creates premade accounts for system.
     * @param key used to identify if it's a seller or buyer
     */
    private void createPremadeAccounts(String key){

        // if the object needed is a buyer...
        if(key.equals("BuyerList"))
        {
            int count = 0;
            this._buyerAccounts = new ArrayList<>();
            // create a bunch of buyers and add them to list
            for(int i= 0; i < MAX_BACCOUNT_NUM; i++)
            {
                BuyerAccount buyer = new BuyerAccount("bUser"+i,"123abc"+i);

                // for every 3rd buyer...
                if(count % 3 == 0)
                {
                    // give them a credit card
                    // note: they all have the same expiration date
                    buyer.addcCard("10001234567891"+ i,"01/12/18");
                }
                this._buyerAccounts.add(buyer);
                count++;
            }


        }
        else if (key.equals("SellerList"))// if it's a seller...
        {
            this._sellerAccounts = new ArrayList<>();
            // create a bunch of sellers and add them to list
            for(int i= 0; i < MAX_SACCOUNT_NUM; i++)
            {

                SellerAccount seller = new SellerAccount("sUser"+i,"s123abc"+i);

                seller.prepopulateInventory();
                this._sellerAccounts.add(seller);
            }

        }
        else
        {
            System.out.println("Invalid key used.") ;
        }

    }

    /**
     * Mutator for isSeller so AccountList will look only at the seller accounts.
     * @param _isSeller a boolean to know if the AccountList is looking for a seller
     */
    public void set_isLookingForSeller(boolean _isSeller) {
        this._isLookingForSeller = _isSeller;
    }

    /**
     * Accessor to see if this object has been reset yet.
     * @return
     */
    public boolean isReset(){ return this._isreset;}




    /**
     * Created by Malissa on 3/29/2015.
     */
    private class AccountListIterator implements Iterator {

        int index;

        public AccountListIterator()
        {
            index =0;
        }
        @Override
        public boolean hasNext() {

            if(_isLookingForSeller && index < _sellerAccounts.size())
            {
                return true;
            }
            else if(!_isLookingForSeller && index < _buyerAccounts.size())
            {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(hasNext())
            {
                if(_isLookingForSeller)
                    return _sellerAccounts.get(index++);
                else
                    return _buyerAccounts.get(index++);
            }
            throw new NoSuchElementException("There are no more elements to get");
            //return null;
        }

        @Override
        public void remove() {
            if(_isLookingForSeller)
                 _sellerAccounts.remove(--index);
            else
                 _buyerAccounts.remove(--index);

        }

    }
}
