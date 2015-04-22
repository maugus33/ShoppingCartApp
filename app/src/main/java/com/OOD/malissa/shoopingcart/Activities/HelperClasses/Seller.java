package com.OOD.malissa.shoopingcart.Activities.HelperClasses;

import android.content.Intent;

import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Activities.Interfaces.UserType;
import com.OOD.malissa.shoopingcart.Activities.Login;

/**
 * Created by Malissa on 3/29/2015.
 * Strategy Pattern implementation for deciding what to do when logged in as a Seller
 */
public class Seller implements UserType {
    /**
     * Sets up the BrowseList as a buyer by passing the
     * BUYER user enum when starting the BrowseList activity.
     * @param userEnum a User enum that acts as a key to start
     *                 the BrowseList as a Buyer or Seller
     */
    @Override
    public void setUp(User userEnum) {
        Intent i = new Intent(Login.getAppContext(), BrowseList.class);
        i.putExtra("User", userEnum);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Login.getAppContext().startActivity(i);

    }
}
