package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Malissa on 3/29/2015.
 * abstract class as there are no objects that are just accounts
 */
abstract class Account implements Serializable{

    //region INSTANCE VARIABLES
    protected String _username;
    protected String _password;
    protected User _accountType;
    //endregion

    //Accessors
    public String getUsername(){

        return _username;
    }

    public String getPassword(){

        return _password;
    }

    /**
     * Returns an ArrayList of String containing the
     * account information. 4/19/15
     * @return an ArrayList of String with account info.
     */
    public ArrayList<String> toArrayList() {
        ArrayList<String> accInfo = new ArrayList<>();
        accInfo.add(_username);
        accInfo.add(_password);
        return accInfo;
    }

}
