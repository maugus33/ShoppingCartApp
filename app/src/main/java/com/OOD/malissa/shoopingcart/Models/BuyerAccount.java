package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;

import java.util.ArrayList;

/**
 * Created by Malissa on 3/29/2015.
 */
public class BuyerAccount extends Account {

    private ArrayList<CreditCard> _cCards;
    private double _bill;

    public BuyerAccount(String username, String password){

        super._username = username;
        super._password = password;
        super._accountType = User.BUYER;
        this._bill = 0.0;
        this._cCards = new ArrayList<CreditCard>();

    }


    //region Accessors
    public double getBill() {return _bill; }
    public ArrayList<CreditCard> getcCards() {return _cCards;}
    public String getUsername() { return super._username;}
    public String getPassword() { return super._password;}
    public User getAccountType() { return super._accountType;}
    //endregion

    //region Mutators
    public void setBill(double bill) {this._bill += bill;}
    public void setUsername(String username) { super._username = username;}
    public void setPassword(String password) { super._password = password;}
    //endregion

    /**
     * Adds a creditCard to list of cards
     * @param accountNum the account Num of the card
     * @param expiry the expiration date of the card
     * Todo: add regular expression logic to check pattern of accountNum and expiry
     */
    public void addcCard(String accountNum, String expiry){
        CreditCard card = new CreditCard(accountNum,expiry);
        this._cCards.add(card);
    }
}
