package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Models.HelperClasses.IDSellerNDate;
import com.OOD.malissa.shoopingcart.Models.Interfaces.IDAlgorithm;
import com.OOD.malissa.shoopingcart.Models.Interfaces.priceObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Inventory implements Serializable , Iterable {

    private ArrayList<Product> _productList;
    private ArrayList<String> _productNumList;
    private IDAlgorithm _productIDAlgo;
    private priceObserver financeWatcher;// In this case, this is the seller that is watching to see if the inventory gets changed

    public Inventory(){

        this._productList = new ArrayList<>();
        this._productNumList = new ArrayList<>();
        this._productIDAlgo = new IDSellerNDate(); // use the seller id and current date to come up with the product id
    }

    /**
     *  Function used to set the price observer to observe when the inventory price changes.
     * @param sellerObserver a priceObserver to be set in financeWatcher
     * @author Malissa Augustin
     */
    public void setPriceObserver(priceObserver sellerObserver) {this.financeWatcher = sellerObserver;}

    /**
     * A method that obtains a new instance of the InventoryIterator.
     * @return an Iterator for the Inventory
     * @author Malissa Augustin
     */
    @Override
    public Iterator iterator() {
        return new InventoryIterator();
    }

    /**
     * Function used to get a new product id using a certain algorithm.
     * @param sellerID The seller ID to be set into the new Product
     * @return String that is the Product's ID
     * @author Malissa Augustin
     */
    public String getNewProductID(String sellerID)
    {
        boolean isUnique;
        String newID;
        do {
            isUnique = true;
            newID = this._productIDAlgo.calculate(sellerID);
            for (String id : _productNumList) {
                // ensure that this id is unique
                if(id.equals(newID))
                {
                    // if it's equal, try to calculate a new id again
                    isUnique = false;
                }
            }
        }while(!isUnique);

        return newID;
    }

    /**
     * Adds the item to the inventory and updates the productNum list.
     * @param item the new Product to be added to the Inventory
     *             @author Malissa Augustin
     */
    public void addItem(Product item)
    {
        this._productList.add(item);
        this._productNumList.add(item.get_ID());
        // update seller account
        notifyPriceObserver(0.0,item.get_invoiceP()*item.get_quantity());

    }

    /**
     *  Notify priceObserver that there has been a change in inventory.
     * @param revenue a double that is the revenue of a Product
     * @param cost a double that is the cost of a Product
     * @author Malissa Augustin
     */
    private void notifyPriceObserver(double revenue, double cost)
    {
        this.financeWatcher.update(revenue,cost);
    }

    /**
     * Seller removes item from inventory.
     * @param item the Product to be removed
     * @author Malissa Augustin
     */
    public void removeItem(Product item){
        int loc = _productList.indexOf(item);

        _productList.remove(item);
        System.out.print("item removed");
        notifyPriceObserver(0.0, -(item.get_invoiceP() *item.get_quantity()) );

    }

    //The Iterator of the Inventory
    private class InventoryIterator implements Iterator {
        // the current index the user is on
        int index;
        public InventoryIterator()
        {
            this.index = 0;
        }

        /**
         * Determines whether another product is available in the list.
         * @return a boolean that is true if there is another item available
         * @author Malissa Augustin
         */
        @Override
        public boolean hasNext() {
            if(index < _productList.size())
                return true;
            return false;
        }

        /**
         * Obtains the next Object in the list then increments the iterator.
         * @return the next Object in the list
         * @author Malissa Augustin
         */
        @Override
        public Object next() {
            if(this.hasNext()){
                return _productList.get(this.index++);
            }
            throw new NoSuchElementException("There are no more elements to get");
        }

        /**
         * Removes the last element returned by the iterator
         * This method can be called only once per call to next().
         * @author Malissa Augustin
         */
        @Override
        public void remove() {
            _productList.remove(--this.index);
            System.out.print("item removed");

        }

    }
}
