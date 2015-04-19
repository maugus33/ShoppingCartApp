package com.OOD.malissa.shoopingcart.Models.HelperClasses;

import com.OOD.malissa.shoopingcart.Models.Interfaces.IDAlgorithm;

import java.util.Date;

/**
 * Created by Malissa on 4/19/2015.
 * algorithm for calculating an id using the seller's ID and the current date
 */
public class IDSellerNDate implements IDAlgorithm {
    @Override
    public String calculate(String key) {
        // get the current date and convert to a string
        Integer i = new Integer((int) (new Date().getTime()/1000));
         //append the date to the key
        String newID = key.concat(i.toString());

        return newID;
    }
}
