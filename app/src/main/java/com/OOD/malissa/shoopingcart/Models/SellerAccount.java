package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;

/**
 * Created by Malissa on 3/29/2015.
 */
public class SellerAccount extends Account {

    private double _costs;
    private double _profits;
    private double _revenues;
    private String _sellerID;

    public SellerAccount(){

        super._accountType = User.SELLER;
    }

    public void calculateFinance(){

    }

    //region Accessors
    public double get_costs() {return _costs;}
    public double get_profits() {return _profits;}
    public double get_revenues() {return _revenues;}
    public String get_sellerID() {return _sellerID;}
    public String getUsername() { return super._username;}
    public String getPassword() { return super._password;}
    public User getAccountType() { return super._accountType;}
    //endregion

    //region Mutators
    public void set_costs(double _costs) {this._costs = _costs;}
    public void set_profits(double _profits) {this._profits = _profits;}
    public void set_revenues(double _revenues) {this._revenues = _revenues;}
    public void set_sellerID(String _sellerID) {this._sellerID = _sellerID;}
    public void setUsername(String username) { super._username = username;}
    public void setPassword(String password) { super._password = password;}
    //endregion


}
