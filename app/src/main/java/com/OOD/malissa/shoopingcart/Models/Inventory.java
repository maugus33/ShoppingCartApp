package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Models.Interfaces.Initializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Malissa on 3/29/2015.
 */
public class Inventory implements Serializable , Iterable, Initializable {

    private ArrayList<Product> ProductList;

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void initialized(Object object, String key) {

    }

    private class InventoryIterator implements Iterator {
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
