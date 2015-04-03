package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Models.Interfaces.Initializable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Malissa on 3/29/2015.
 */
public class AccountList implements Iterable, Initializable {

    // if you want to make code more readable, you can use the region/endregion comment to
    //be able to contract blocks of code . Example below!

    //region INSTANCE VARIABLES
    ArrayList<BuyerAccount> _buyerAccounts;
    ArrayList<SellerAccount> _sellerAccounts;
    boolean _isSeller;
    //endregion

    @Override
    public Iterator iterator() {

        return new AccountListIterator(_isSeller);
    }

    @Override
    public void initialized(Object object, String key) {

    }

    /**
     * A mutator that sets the _isSeller boolean instance.
     * @param isSeller a boolean that determines whether the user
     *        is a buyer or a seller.
     */
    public void set_isSeller(boolean isSeller){
        _isSeller = isSeller;
    }

    /**
     * Created by Malissa on 3/29/2015.
     */
    private class AccountListIterator implements Iterator {

        //Create an index variable and forSeller boolean
        int index = 0;
        boolean forSeller;

        /**
         * A constructor for the AccountListIterator. If the login is
         * for a seller, use for sellerAccounts. Else, use for buyerAccounts.
         * @param isSeller
         */
        AccountListIterator(boolean isSeller){
            forSeller = isSeller;
        }

        /**
         * If the iterator is initialized for sellers, search
         * sellerAccounts, else search buyerAccounts.
         * @return
         */
        @Override
        public boolean hasNext() {
            if(forSeller){
                if(index < _sellerAccounts.size()) {
                    return true;
                }

                return false;
            }

            if(index < _buyerAccounts.size()){
                return true;
            }

            return false;

        }

        @Override
        public Object next() {

            if(this.hasNext()){
                if(forSeller){
                    return _sellerAccounts.get(index++);
                }

                return _buyerAccounts.get(index++);
            }
            return null;
        }

        @Override
        public void remove() {

           //Not yet implemented

        }

        /**
         * Set iterator to the first item in list and returns it and goes to the next item
         * @return The first item in list
         */
        public Object first() {
             index = 0;

           return next();
        }
        /**
         * Returns the currentItem the iterator is set on
         * @return The first item in list
         */
        public Object currentItem() {

            if(forSeller) {
                return _sellerAccounts.get(index);
            }

            return _buyerAccounts.get(index);
        }


    }
}
