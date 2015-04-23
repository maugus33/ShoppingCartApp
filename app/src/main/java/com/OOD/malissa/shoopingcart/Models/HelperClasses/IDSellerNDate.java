package com.OOD.malissa.shoopingcart.Models.HelperClasses;

import com.OOD.malissa.shoopingcart.Models.Interfaces.IDAlgorithm;

import java.util.Date;

/**
 * algorithm for calculating an id using the seller's ID and the current date
 */
public class IDSellerNDate implements IDAlgorithm {

    /**
     * A method that creates an ID using the seller ID and the current date for product IDs.
     * @param key a String that is the seller's ID
     * @return a String that is a new ID number for Products
     * @author Malissa Augustin
     */
    @Override
    public String calculate(String key) {
        // get the current date and convert to a string
        Integer i = new Integer((int) (new Date().getTime()/1000));
         //append the date to the key
        String newID = key.concat(i.toString());

        return newID;
    }
}
