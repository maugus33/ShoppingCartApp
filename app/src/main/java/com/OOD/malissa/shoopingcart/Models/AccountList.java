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
    //endregion

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void initialized(Object object, String key) {

    }
    /**
     * Created by Malissa on 3/29/2015.
     */
    private class AccountListIterator implements Iterator {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

        @Override
        public void remove() {

        }

        /**
         * Set iterator to the first item in list and returns it and goes to the next item
         * @return The first item in list
         */
        public Object first() {
            return null;
        }
        /**
         * Returns the currentItem the iterator is set on
         * @return The first item in list
         */
        public Object currentItem() {
            return null;
        }


    }
}
