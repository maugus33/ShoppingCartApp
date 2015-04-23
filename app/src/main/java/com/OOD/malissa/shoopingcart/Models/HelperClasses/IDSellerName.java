package com.OOD.malissa.shoopingcart.Models.HelperClasses;

import com.OOD.malissa.shoopingcart.Models.Interfaces.IDAlgorithm;

/**
 * algorithm used to calculate an id using the seller's name
 */
public class IDSellerName implements IDAlgorithm {

    /**
     * A method used to calculate the ID number of a seller.
     * @param key a String that is the seller's username
     * @return a String that is the seller's ID number
     * @author Malissa Augustin
     */
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
