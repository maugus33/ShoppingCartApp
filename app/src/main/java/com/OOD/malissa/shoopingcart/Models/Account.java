package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;

import java.io.Serializable;

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


}
