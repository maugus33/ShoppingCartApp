package com.OOD.malissa.shoopingcart.Controllers;

import android.content.Context;
import android.content.Intent;

import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Activities.Checkout;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Interfaces.CartObserver;
import com.OOD.malissa.shoopingcart.Activities.Payment;
import com.OOD.malissa.shoopingcart.Activities.ShoppingCart;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.BuyerAccount;
import com.OOD.malissa.shoopingcart.Models.Cart;
import com.OOD.malissa.shoopingcart.Models.CreditCard;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Malissa on 3/29/2015.
 */
public class BuyerClerk extends StoreClerk {


    //region INSTANCE VARIABLE

    private Cart _shoppingCart;
    private NewIterator _currentInventoryIter;
    private NewIterator _sellerIterator;
    // decimal format used to properly format the doubles
    private DecimalFormat df = new DecimalFormat("0.00");

    //endregion

    //region SINGLETON SETUP
    private static BuyerClerk ourInstance = new BuyerClerk();

    public static BuyerClerk getInstance() {
        return ourInstance;
    }

    private BuyerClerk() {
        super();
        _shoppingCart = null;
        _currentInventoryIter = null;
        _sellerIterator = null;
    }
    //endregion

    /**
     * Resets the object for logging out.
     */
    @Override
    public void reset() {

        //if there is a reference to the accountlist and it has not been reset yet...
        if(this._accList != null && !this._accList.isReset())
        {
            //reset it.
            this._accList.reset();
        }

        this._shoppingCart = null;
        this._user = null;
        this._userAccountB = null;
        this._userAccountS = null;
        this._currentInventory = null;
    }


    /**
     * Adds a product to the shopping cart.
     * @param item a Product to be added to the shopping cart.
     */
    public void addToCart(Product item){
        _shoppingCart.addItem(item);

    }

    /**
     * Function used to add a product to cart given the product id and seller id
     * @param productID a String that represents the product's ID number
     * @param SellerID a String that represents the product's seller's ID number
     */
    public void addToCart(String productID, String SellerID) {
        boolean foundProduct = false;// used to see if the desired product has been found yet
        // grab next Inventory if null

        if (_currentInventoryIter == null) {
            setNextInventory();
        }
        while(foundProduct == false) {
            //while the current inventory has products and the product is not found yet
            while (_currentInventoryIter.hasNext() && foundProduct == false) {

                //get the next product
                Product item = (Product) _currentInventoryIter.next();
                // see if the product matches the product name, sellerID and productID
                if(item.equals(productID,SellerID))
                {
                    // product found. add to cart
                    _shoppingCart.addItem(item);
                    //product was found
                    foundProduct = true;
                }
            }
            try {// get the next inventory only if there is no more products and the product wasn't found yet
                if(!_currentInventoryIter.hasNext() && foundProduct == false) {
                    _currentInventoryIter = null;
                    setNextInventory();
                }

            }
            catch(NoSuchElementException e)// no more seller accounts. should not enter this catch statement!
            {
                //set current inventory as null
                _currentInventoryIter = null;
                _sellerIterator = null;
                System.out.println("Could not find product in inventory ");
                e.printStackTrace();

            }
        }
        // reset current inventory Iterator
        _currentInventoryIter = null;
        // rest selleriterator
        _sellerIterator = null;


    }

    /**
     * Removes a given item from the Shopping Cart.
     * @param item the item to be removed from the shopping cart.
     */
    public void removeFromCart(Product item){
        _shoppingCart.removeItem(item);
        }

    /**
     * A method that calls the ShoppingCart screen from the BrowseList.
     */
    public void showShoppingCart(){
        Intent i = new Intent(BrowseList.getAppContext(), ShoppingCart.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BrowseList.getAppContext().startActivity(i);
    }

    /**
     * A method that updates the count of a Product in the Shopping Cart.
     * @param item the Product in the cart that will be updated
     * @param quantity an int that will be the new count
     */
    public void updateCartCount(Product item, int quantity){
        _shoppingCart.updateCount(item, quantity);
    }

    /**
     * Obtains the Cart's current Cart count.
     * @return an int that is the count of the cart.
     */
    public  int getCartCount(){
        if(_shoppingCart != null)
            return _shoppingCart.getCount();
        return 0;
   }

    /**
     * Obtains the count of a Product in the Cart.
     * @param item the Product whose count is needed
     * @return an int that is the count of the given product.
     */
    public int getItemCount(Product item) {
        return _shoppingCart.itemCount(item);
    }

    /**
     * A method that calls the Checkout screen from the Payment screen.
     */
    public void finalCheckout(){
        Intent i = new Intent(Payment.getAppContext(), Checkout.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Payment.getAppContext().startActivity(i);

    }

    /**
     * A method that obtains the account numbers of the the current buyer's
     * account.
     * @return an ArrayList<String> that has all of the account numbers.
     */
    public ArrayList<String> getCreditInfo() {
        ArrayList<String> accountNumbers = new ArrayList<String>();
        ArrayList<CreditCard> cCards = StoreClerk.getInstance()._userAccountB.getcCards();
        for(int i = 0; i < cCards.size(); i++) {
            accountNumbers.add(cCards.get(i).getAccNumber());
        }
        return accountNumbers;
    }

    /**
     * Adds a new credit card to the current buyer's account given the account number and expiration.
     * @param accNum a String that represents the account number of the new card
     * @param expiration a String that represents the expiration of the new card
     */
    public void addNewCredit(String accNum, String expiration){
        StoreClerk.getInstance()._userAccountB.addcCard(accNum, expiration);
    }

    /**
     * A method that calls the Payment screen from the ShoppingCart.
     */
    public void getVerifyPurchase(){
        Intent i = new Intent(ShoppingCart.getAppContext(), Payment.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ShoppingCart.getAppContext().startActivity(i);

    }

    /**
     * A method that takes the current shopping cart, pays the proper seller found
     * by the product's seller ID, bills the current buyer, and modifies the Product's
     * available quantity. The Shopping Cart is emptied as the Products are being looped.
     * The buyer and seller accounts in Storage are then updated to save the changes
     * onto the serial database..
     */
    public void paySeller(){
        while(!_shoppingCart.isEmpty()) {
            Product prod = _shoppingCart.getFirstProd();
            int count = _shoppingCart.getFirstCount();

            AccountList accList = StoreClerk.getInstance()._accList;

            accList.set_isLookingForSeller(true);

            for (Iterator iter = _accList.iterator(); iter.hasNext(); ) {
                SellerAccount seller = (SellerAccount) iter.next();

                if (prod.get_SellerID().equals(seller.get_sellerID())) {
                    // if the seller of the product is found...

                    //update seller finance info
                    seller.update(prod.get_sellingP() * count,0.0);

                    //update buyer bill
                    this._userAccountB.setBill(prod.get_sellingP() * count);

                    // update product quantity

                    if((prod.get_quantity() - count)< 0)
                    {
                        throw new IllegalArgumentException("quantity purchased is more than what is there.");
                    }
                    else {
                        // update product quantity
                        prod.set_quantity(prod.get_quantity() - count);
                    }

                    //updateaccount info for seller and buyer
                    this.updateStorage("SellerList");
                    this.updateStorage("BuyerList");

                }
            }
        }

    }

    /**
     *A method that obtains the current receipt of the items in the shopping cart.
     * @return an ArrayList<String> that represents the receipt to be shown.
     */
    //Made to return ArrayList to reflect changes in Cart 4/19/15
    public ArrayList<String> getReceipt() {
        return _shoppingCart.printReceipt();
    }


    /**
     * A method that calls the BrowseList from any screen given its intent.
     * @param context the Context of the screen that will open the BrowseList
     */
    public void openBrowseList(Context context) {
        Intent i = new Intent(context, BrowseList.class);
        i.putExtra("User", User.BUYER);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

    /**
     * Function used to get the current items in the cart
     * @return a copy of what's in the model
     */
    public ArrayList<Product> getCartItems(){

        ArrayList<Product> cartItems = new ArrayList<Product>();

        for (int i = 0; i < _shoppingCart.getCartQuantity(); i++) {
            cartItems.add(_shoppingCart.getCartItems(i));
        }

        return cartItems;
    }

    /**
     * Grabs the next inventory from the next seller account in AccountList
     */
    private void setNextInventory(){

        // if uninitialized...
        if(_sellerIterator == null)
        {
            // get a new iterator and have the accList only look through sellers
           _sellerIterator = _accList.iterator(true);
        }

        // point to the next seller and grab their inventory

        SellerAccount s = (SellerAccount) _sellerIterator.next();
        // setup an iterator for this new inventory
        _currentInventoryIter = s.get_InventoryIterator();

    }

    /**
     * A method that retrieves the next product from the current.
     * @return returns a product or null if there is no more.
     */
    public Product getAStoreProd(){
        // grab next Inventory if null

        if (_currentInventoryIter == null) {
            setNextInventory();
        }

        //then grab the next product and return it
        try{
           return (Product)_currentInventoryIter.next();
        }
        catch(NoSuchElementException ex)// no more products
        {
            //set current inventory as null
            _currentInventoryIter = null;
            // grab next Inventory
            try {

                setNextInventory();
                return (Product)_currentInventoryIter.next();
            }
            catch(NoSuchElementException e)// no more seller accounts
            {
                //set current inventory as null
                _currentInventoryIter = null;
                _sellerIterator = null;
                return null;
            }

        }

    }

    /**
     * Passes on needed info to BuyerClerk. also where the cart is created for this session.
     * @param user the User enum that determines whether a Buyer or Seller logged in
     * @param buyer the current buyer account that is logged in
     */
    public void setUpUser(User user, BuyerAccount buyer) {
        super._user = user;
        super._userAccountB = buyer;

        _shoppingCart = new Cart();

    }

    /**
     * Set the Shopping Cart's observer to be able to show the current Cart count.
     * @param ob the CartObserver class that is used to monitor the count of the cart
     */
    public void setCartObserver(CartObserver ob)
    {
        _shoppingCart.setObserver(ob);
    }

    /**
     * A method that obtains the current buyer account's bill.
     * @return a String that represents the buyer account's bill
     */
    public String getBuyerBill() {
        return "$" + df.format(this._userAccountB.getBill());
    }
}
