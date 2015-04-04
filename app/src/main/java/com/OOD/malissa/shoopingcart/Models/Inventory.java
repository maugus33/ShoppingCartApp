package com.OOD.malissa.shoopingcart.Models;

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
public class Inventory implements Serializable , NewIterable, Initializable {

    private ArrayList<Product> _productList;

    public Inventory(){
        this._productList = new ArrayList<>();
    }

    @Override
    public NewIterator iterator() {
        return new InventoryIterator();
    }

    // might not need to be implemented
    @Override
    public void initialized(Object object, String key) {

    }

    /** todo: Paul, should be put this method in the nested class? or nah?
     * Adds the item to the inventory
     * @param item
     */
    public void addItem(Product item)
    {
        this._productList.add(item);
    }

    /**
     * Adds the item to the inventory
     * @param item
     * @param iter the iterator associated with this inventory
     */
    public void removeItem(Product item, InventoryIterator iter){
        int loc = _productList.indexOf(item);
        if(loc <= iter.index)
            iter.index--;

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
