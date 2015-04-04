package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Models.Interfaces.Initializable;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterable;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Malissa on 3/29/2015.
 */
public class AccountList implements NewIterable, Initializable {

    //region SINGLETON SETUP
    private static AccountList ourInstance = new AccountList();

    public static AccountList getInstance() {
        return ourInstance;
    }


    private AccountList() {
        _buyerAccounts = null;
        _sellerAccounts = null;
        _isLookingForSeller = false;
    }
    //endregion

    // constant variable for account creation
    final private int MAX_BACCOUNT_NUM =  10;
    final private int MAX_SACCOUNT_NUM =  3;
    // if you want to make code more readable, you can use the region/endregion comment to
    //be able to contract blocks of code . Example below!

    //region INSTANCE VARIABLES
    ArrayList<BuyerAccount> _buyerAccounts;
    ArrayList<SellerAccount> _sellerAccounts;
    boolean _isLookingForSeller;
    //endregion

    @Override
    public NewIterator iterator() {
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
     * Function which creates premade accounts for system
     * @param key used to identify if it's a seller or buyer
     */
    private void createPremadeAccounts(String key){

        AccountFactory factory = new AccountFactory();
        // if the object needed is a buyer...
        if(key.equals("BuyerList"))
        {
            int count = 0;
            this._buyerAccounts = new ArrayList<>();
            // create a bunch of buyers and add them to list
            for(int i= 0; i < MAX_BACCOUNT_NUM; i++)
            {
                BuyerAccount buyer = factory.getBuyerAccount("bUser"+i,"123abc"+i);

                // for every 3rd buyer...
                if(count % 3 == 0)
                {
                    // give them a credit card
                    // note: they all have the same expiration date
                    buyer.addcCard("10001234567891"+ i,"01/12/18");
                }
                this._buyerAccounts.add(buyer);
            }


        }
        else if (key.equals("SellerList"))// if it's a seller...
        {
            this._sellerAccounts = new ArrayList<>();
            // create a bunch of sellers and add them to list
            for(int i= 0; i < MAX_SACCOUNT_NUM; i++)
            {// todo: create products for each seller

                SellerAccount seller = factory.getSellerAccount("sUser"+i,"s123abc"+i);
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
     * Created by Malissa on 3/29/2015.
     */
    private class AccountListIterator implements NewIterator {

        int index;

        public AccountListIterator()
        {
            index =0;
        }
        @Override
        public boolean hasNext() {

            if(_isLookingForSeller && this.index < _sellerAccounts.size())
            {
                return true;
            }
            else if(!_isLookingForSeller && this.index < _buyerAccounts.size())
            {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext())
            {
                if(_isLookingForSeller)
                    return _sellerAccounts.get(this.index);
                else
                    return _buyerAccounts.get(this.index);
            }
            return null;
        }

        @Override
        public void remove() {
            if(_isLookingForSeller)
                 _sellerAccounts.remove(--this.index);
            else
                 _buyerAccounts.remove(--this.index);

        }

        /**
         * Set iterator to the first item in list and returns it and goes to the next item
         * @return The first item in list
         */
        @Override
        public Object first() {
            this.index = 0;
            return this.next();
        }
        /**
         * Returns the currentItem the iterator is set on
         * @return The first item in list
         */
        @Override
        public Object currentItem() {

            if(_isLookingForSeller)
                return _sellerAccounts.get(this.index);
            else
                return _buyerAccounts.get(this.index);
        }


    }
}
