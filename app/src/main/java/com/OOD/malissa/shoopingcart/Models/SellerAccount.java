package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Models.HelperClasses.IDSellerName;
import com.OOD.malissa.shoopingcart.Models.Interfaces.IDAlgorithm;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Malissa on 3/29/2015.
 */
public class SellerAccount extends Account {

    private double _costs;
    private double _profits;
    private double _revenues;
    private String _sellerID;
    private Inventory _items;
    private IDAlgorithm _sellerIDAlgo;

    public SellerAccount(String username, String password){

        super._username = username;
        super._password = password;
        super._accountType = User.SELLER;
        this._costs = 0.0;
        this._profits = 0.0;
        this._revenues = 0.0;
        this._items = new Inventory();
        _sellerIDAlgo = new IDSellerName();// use this algo to calculate the seller id

        //calculate SellerID
        calculateSellerID();
    }

    // removes item from inventory
    public void removeItem(Product item)
    {
        this._items.removeItem(item);
    }
    /**
     * Function used to create sellerID based on the Unique user id
     */
    private void calculateSellerID() {
        // use the seller algo to calculate the sellerID
        this._sellerID = _sellerIDAlgo.calculate(super._username);


    }

    public String calculateProductID() {
        // use the product algo to calculate a new product ID
        return this._items.getNewProductID(this._sellerID);


    }

    // adds a new product to inventory
    public void addProduct(Product item){
        this._items.addItem(item);
    }
    public void calculateFinance(){

    }

    /**
     * Function used to setup inventory with premade products
     */
    public void prepopulateInventory(){
        ArrayList<Product> temp = new ArrayList<>();

        // max prepopulated item count: 10
        temp.add(new Product("Hammer", "1", "\"It hits things\"", "Tools", 5, 3.00, 5.00, this._sellerID));
        temp.add(new Product("Nail", "2", "\"It holds things together\"", "Tools", 3, 1.00, 2.00, this._sellerID));
        temp.add(new Product("Soap", "3", "\"It washes things\"", "Hygiene", 30, 2.00, 6.00, this._sellerID));
        temp.add(new Product("Picture", "4", "\"It looks nice\"", "Decor", 15, 10.00, 15.00, this._sellerID));
        temp.add(new Product("TextBook", "5", "\"It Makes you smarter\"", "Books", 20, 20.00, 50.00, this._sellerID));
        temp.add(new Product("Cookie", "6", "\"It's a treat\"", "Food", 40, 0.50, 2.00, this._sellerID));
        temp.add(new Product("Egg", "7", "\"Comes from chickens\"", "Food", 55, 0.20, 0.70, this._sellerID));
        temp.add(new Product("Plastic Gnome", "8", "\"For your lawn\"", "Decor", 5, 3.00, 5.00, this._sellerID));
        temp.add(new Product("Pillow", "9", "\"For your head\"", "Bedding", 53, 3.00, 5.00, this._sellerID));
        temp.add(new Product("Shoes", "10", "\"For walking with\"", "Clothing", 13, 15.00, 25.00, this._sellerID));

        // random num gen between 1 and max number of products
        // reference: http://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java
        Random rn = new Random();
        int MaxNum = rn.nextInt(temp.size() ) + 1;

        //add random number of item to seller's inventory
        for(int i = 0; i< MaxNum; i++)
        {
            // add item to inventory
            this._items.addItem(temp.get(i));
            // add product id to inventory

        }


    }

    //region Accessors
    public double get_costs() {return _costs;}
    public double get_profits() {return _profits;}
    public double get_revenues() {return _revenues;}
    public String get_sellerID() {return _sellerID;}
    public String getUsername() { return super._username;}
    public String getPassword() { return super._password;}
    public User getAccountType() { return super._accountType;}
    public NewIterator get_InventoryIterator() { return _items.iterator();} //Made this return a new inventory iterator 4/19/15
    //When reopening browseList, the selleracount inventoryiterator is still set to last item. so, a new iterator each
    //call will make sure that it always starts at the beginning of the inventory.
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
