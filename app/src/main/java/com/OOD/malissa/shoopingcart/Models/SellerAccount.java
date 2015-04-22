package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Models.HelperClasses.IDSellerName;
import com.OOD.malissa.shoopingcart.Models.Interfaces.IDAlgorithm;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;
import com.OOD.malissa.shoopingcart.Models.Interfaces.priceObserver;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Malissa on 3/29/2015.
 */
public class SellerAccount extends Account implements priceObserver{

    private double _costs;
    private double _profits;
    private double _revenues;
    private String _sellerID;
    private Inventory _items;
    private static int prepopCount = 0;
    private IDAlgorithm _sellerIDAlgo;
    // decimal format used to properly format the doubles
    private DecimalFormat df = new DecimalFormat("0.00");

    /**
     * A constructor for the SellerAccount.
     * @param username a String that is the username of the account
     * @param password a String that is the password of the account
     */
    public SellerAccount(String username, String password){

        super._username = username;
        super._password = password;
        super._accountType = User.SELLER;
        this._costs = 0.0;
        this._profits = 0.0;
        this._revenues = 0.0;
        this._items = new Inventory();
        this._items.setPriceObserver(this);
        //subscribe this seller's inventory as an observer
        _sellerIDAlgo = new IDSellerName();// use this algo to calculate the seller id

        //calculate SellerID
        calculateSellerID();
    }

    /**
     * A mthod that removes the given Product from the Inventory.
     * @param item the Product to be removed from the Inventory
     */
    public void removeItem(Product item)
    {
        this._items.removeItem(item);
    }

    /**
     * Function used to create sellerID based on the Unique user id.
     */
    private void calculateSellerID() {
        // use the seller algo to calculate the sellerID
        this._sellerID = _sellerIDAlgo.calculate(super._username);


    }

    /**
     * A method used to create a Product ID using the Product algorithm.
     * @return a String that is the Product's ID
     */
    public String calculateProductID() {
        // use the product algo to calculate a new product ID
        return this._items.getNewProductID(this._sellerID);
    }

    /**
     * A method that adds a Product to the seller's Inventory.
     * @param item the Product to be added
     */
    public void addProduct(Product item){
        this._items.addItem(item);
    }


    /**
     * Function used to get finance info to show.
     * @return a String that shows the finances
     */
    public String getFinances() {
        String financeInfo = "";
        //get profit
        //if(this.get_profits() == 0.0) financeInfo += "Profit:\t\t\t$ " + 0.00 + "\n";
        //else
            financeInfo += "Profit:\t\t\t$ " + df.format(this.get_profits())+ "\n";
        //get cost
        //if(this.get_costs() == 0.0) financeInfo += "Cost:\t\t\t$ " + 0.00 + "\n";
       //else
       financeInfo += "Cost:\t\t\t$ " + df.format(this.get_costs())+ "\n";
        //get revenue
        //if(this.get_revenues() == 0.0) financeInfo += "Revenue:\t\t$ " + 0.00 + "\n";
        //else
        financeInfo += "Revenue:\t\t$ " + df.format(this.get_revenues())+ "\n";
        //
        return financeInfo;
    }

    /**
     * Function used to setup inventory with premade products.
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
        //temp.add(new Product("Shoes", "10", "\"For walking with\"", "Clothing", 13, 15.00, 25.00, this._sellerID));

        int i = 0;
       while(i < 3 && prepopCount < 10)
       {
           this._items.addItem(temp.get(prepopCount++));
           i++;
       }

    }

    //region Accessors

    /**
     * An accessor to obtain the SellerAccount's costs.
     * @return a double that is the total costs of the Products in the Inventory
     */
    public double get_costs() {return _costs;}

    /**
     * An accessor to obtain the SellerAccount's profits.
     * @return a double that is the profit made so far by the seller
     */
    public double get_profits() {return _profits;}

    /**
     * An accessor to obtain the SellerAccount's revenues.
     * @return a double that is the amount obtained from a purchase
     */
    public double get_revenues() {return _revenues;}

    /**
     * An accessor to obtain the SellerAccount's sellerID.
     * @return a String that represents the SellerAccount's sellerID
     */
    public String get_sellerID() {return _sellerID;}

    /**
     * An accessor to obtain the SellerAccount's username.
     * @return a String that is the username of the SellerAccount
     */
    public String getUsername() { return super._username;}

    /**
     * An accessor to obtain the SellerAccount's password.
     * @return a String that is the password of the SellerAccount
     */
    public String getPassword() { return super._password;}

    /**
     * An accessor to obtain the SellerAccount's type.
     * @return a User enum that is set to SELLER
     */
    public User getAccountType() { return super._accountType;}

    /**
     * An accessor to obtain the SellerAccount's InventoryIterator.
     * @return a new InventoryIterator for the seller's inventory
     */
    public NewIterator get_InventoryIterator() { return _items.iterator();}
    //endregion

    //region Mutators

    /**
     * A mutator that sets the Seller's costs.
     * @param _costs a double to be set as the _costs
     */
    public void set_costs(double _costs) {this._costs = _costs;}

    /**
     * A mutator that sets the Seller's profits.
     * @param _profits a double to be set as the _profits
     */
    public void set_profits(double _profits) {this._profits = _profits;}

    /**
     * A mutator that sets the Seller's revenues.
     * @param _revenues a double to be set as the _revenues
     */
    public void set_revenues(double _revenues) {this._revenues = _revenues;}

    /**
     * A mutator that sets the Seller's sellerID.
     * @param _sellerID a String to be set as the _sellerID
     */
    public void set_sellerID(String _sellerID) {this._sellerID = _sellerID;}

    /**
     * A mutator that sets the Seller's Username.
     * @param username a String to be set as the _username
     */
    public void setUsername(String username) { super._username = username;}

    /**
     * A mutator that sets the Seller's Password.
     * @param password a String to be as as the _password
     */
    public void setPassword(String password) { super._password = password;}

    /**
     * Updates the finances as the inventory has updated
     * set either of them to zero if not in use.
     */
    @Override
    public void update(double sellingPrice, double cost) {
        this._revenues += sellingPrice;
        this._costs += cost;
        this._profits += (sellingPrice - cost);
    }

    //endregion

}
