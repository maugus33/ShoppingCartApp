package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;

import java.util.ArrayList;

/**
 * Created by Malissa on 3/29/2015.
 */
public class BuyerAccount extends Account {

    private ArrayList<CreditCard> _cCards;
    private double _bill;

    public BuyerAccount(){
        super._accountType = User.BUYER;

    }


    //region Accessors
    public double getBill() {return _bill; }
    public ArrayList<CreditCard> getcCards() {return _cCards;}
    public String getUsername() { return super._username;}
    public String getPassword() { return super._password;}
    public User getAccountType() { return super._accountType;}
    //endregion

    //region Mutators
    public void setBill(double bill) {this._bill = bill;}
    public void setcCards(ArrayList<CreditCard> cCards) {this._cCards = cCards;}
    public void setUsername(String username) { super._username = username;}
    public void setPassword(String password) { super._password = password;}
    //endregion

    /**
     * Adds a creditCard to list of cards
     * @param card new CreditCard information
     */
    public void addcCard(CreditCard card){

    }
}
