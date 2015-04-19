package com.OOD.malissa.shoopingcart.Models.HelperClasses;

import com.OOD.malissa.shoopingcart.Models.Interfaces.IDAlgorithm;

/**
 * Created by Malissa on 4/19/2015.
 * algorithm used to calculate an id using the seller's name
 */
public class IDSellerName implements IDAlgorithm {
    @Override
    public String calculate(String key) {
        String sellerID = "";
        try {
            for (int i = 0; i < key.length(); i++) {
                // grab letter
                char letter = key.charAt(i);
                // convert letter to number and store _sellerID
                sellerID += Character.getNumericValue(letter);

            }
        }
        catch( IllegalStateException e)
        {
            System.out.println("There is an issue creating the sellerID of: " + key);
            e.printStackTrace();
        }

        return sellerID;
    }
}
