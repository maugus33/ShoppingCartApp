package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * abstract class as there are no objects that are just accounts
 */
abstract class Account implements Serializable{

    //region INSTANCE VARIABLES
    protected String _username;
    protected String _password;
    protected User _accountType;
    //endregion

    /**
     * An accessor used to obtain the _username.
     * @return a String that contains the username
     * @author Paul Benedict Reyes
     */
    public String getUsername(){

        return _username;
    }

    /**
     * An accessor used to obtain the _password.
     * @return a String that contains the password
     * @author Paul Benedict Reyes
     */
    public String getPassword(){

        return _password;
    }

    /**
     * Returns an ArrayList of String containing the
     * account information.
     * @return an ArrayList of String with account info.
     * @author Paul Benedict Reyes
     */
    public ArrayList<String> toArrayList() {
        ArrayList<String> accInfo = new ArrayList<>();
        accInfo.add(_username);
        accInfo.add(_password);
        return accInfo;
    }

}
