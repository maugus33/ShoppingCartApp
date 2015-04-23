package com.OOD.malissa.shoopingcart.Models;

import java.io.Serializable;

public class CreditCard implements Serializable {

    private String _accNumber;
    private String _expiration;

    public CreditCard(String accountNum, String expiry){

        this._accNumber = accountNum;
        this._expiration = expiry;
    }


    //region Accessors

    /**
     * An accessor to obtain the account number of the credit card.
     * @return a String that is the account number
     */
    public String getAccNumber() {return _accNumber;}

    /**
     * An accessor to obtain the expiration of the credit card.
     * @return a String that is the expiration date
     */
    public String getExpiration() { return _expiration;}
    //endregion

}
