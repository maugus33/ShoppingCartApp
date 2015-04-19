package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Models.HelperClasses.IDSellerNDate;
import com.OOD.malissa.shoopingcart.Models.HelperClasses.IDSellerName;
import com.OOD.malissa.shoopingcart.Models.Interfaces.IDAlgorithm;
import com.OOD.malissa.shoopingcart.Models.Interfaces.Initializable;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterable;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Malissa on 3/29/2015.
 */
public class Inventory implements Serializable , NewIterable {

    private ArrayList<Product> _productList;
    private ArrayList<String> _productNumList;
    private IDAlgorithm _productIDAlgo;

    public Inventory(){

        this._productList = new ArrayList<>();
        this._productNumList = new ArrayList<>();
        this._productIDAlgo = new IDSellerNDate(); // use the seller id and current date to come up with the product id
    }

    @Override
    public NewIterator iterator() {
        return new InventoryIterator();
    }

    /**
     * Function used to get a new product id using a certain algorithm
     * @param sellerID
     * @return
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
     * Adds the item to the inventory and updates the productNum list
     * @param item
     */
    public void addItem(Product item)
    {
        this._productList.add(item);
        this._productNumList.add(item.get_ID());
    }

    /**
     * Adds the item to the inventory
     * @param item
     */
    public void removeItem(Product item){
        int loc = _productList.indexOf(item);

        _productList.remove(item);
        System.out.print("item removed");

    }
    private class InventoryIterator implements NewIterator {
        // the current index the user is on
        int index;
        public InventoryIterator()
        {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            if(index < _productList.size())
                return true;
            return false;
        }

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
         */
        @Override
        public void remove() {
            _productList.remove(--this.index);
            System.out.print("item removed");

        }


        /**
         * Set iterator to the first item in list and returns it and goes to the next item
         * @return The first item in list
         */
        @Override
        public Object returnFirst() {
            this.index = 0;
            return this.next();
        }

        /**
         * Set iterator to the first item in list
         */
        @Override
        public void first() {
            this.index = 0;
        }
        /**
         * Returns the currentItem the iterator is set on
         * @return The first item in list
         */
        @Override
        public Object currentItem() {

            return _productList.get(this.index);
        }

    }
}
