package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;

import java.util.ArrayList;

/**
 * Created by Malissa on 3/29/2015.
 */
public class BuyerAccount extends Account {

    private ArrayList<CreditCard> _cCards;
    private double _bill;

    /**
     * A constructor for the BuyerAccount.
     * @param username a String that is the username of the account
     * @param password a String that is the password of the account
     */
    public BuyerAccount(String username, String password){

        super._username = username;
        super._password = password;
        super._accountType = User.BUYER;
        this._bill = 0.0;
        this._cCards = new ArrayList<CreditCard>();

    }


    //region Accessors
    /**
     * An accessor to view the Buyer's current bill.
     * @return a double that contains the Buyer's current bill
     */
    public double getBill() {return _bill; }

    /**
     * An accessor to view the Buyer's credit card list.
     * @return an ArrayList of CreditCard that has the Buyer's credit cards
     */
    public ArrayList<CreditCard> getcCards() {return _cCards;}

    /**
     * An accessor to view the BuyerAccount's username.
     * @return a String that shows the Buyer's username
     */
    @Override
    public String getUsername() { return super._username;}

    /**
     * An accessor to view the Buyer's password.
     * @return a String that shows the Buyer's password
     */
    @Override
    public String getPassword() { return super._password;}

    /**
     * An accessor to see obtain the Account type of this account.
     * @return A User enum that is set to BUYER
     */
    public User getAccountType() { return super._accountType;}

    //endregion

    //region Mutators

    /**
     * A mutator to set the Buyer's current bill.
     * @param bill the amount to be added to the bill
     */
    public void setBill(double bill) {this._bill += bill;}

    /**
     * A mutator that changes the username of the account.
     * @param username the String that has the new username to be put in
     */
    public void setUsername(String username) { super._username = username;}

    /**
     * A mutator that changes the password of the account.
     * @param password the String that has the new password to be put in
     */
    public void setPassword(String password) { super._password = password;}
    //endregion

    /**
     * Adds a creditCard to list of cards
     * @param accountNum the account Num of the card
     * @param expiry the expiration date of the card
     */
    public void addcCard(String accountNum, String expiry){
        CreditCard card = new CreditCard(accountNum,expiry);
        this._cCards.add(card);
    }
}
