package com.OOD.malissa.shoopingcart.Models;

/**
 * Created by Malissa on 3/29/2015.
 */
public class CreditCard {

    private String accNumber;
    private String expiration;

    public CreditCard(){

    }


    //region Accessors
    public String getAccNumber() {return accNumber;}
    public String getExpiration() { return expiration;}
    //endregion

    //region Mutators
    public void setAccNumber(String accNumber) {this.accNumber = accNumber;}
    public void setExpiration(String expiration) {this.expiration = expiration;}
    //endregion
}
