package com.OOD.malissa.shoopingcart.Controllers;

import android.content.Context;
import android.content.Intent;

import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Activities.Checkout;
import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Login;
import com.OOD.malissa.shoopingcart.Activities.Payment;
import com.OOD.malissa.shoopingcart.Activities.ShoppingCart;
import com.OOD.malissa.shoopingcart.Models.AccountList;
import com.OOD.malissa.shoopingcart.Models.BuyerAccount;
import com.OOD.malissa.shoopingcart.Models.Cart;
import com.OOD.malissa.shoopingcart.Models.CreditCard;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Malissa on 3/29/2015.
 */
public class BuyerClerk extends StoreClerk {


    //region INSTANCE VARIABLE
    //todo: change location where cart is instantiated
    private Cart _shoppingCart = new Cart();
    private NewIterator _currentInventoryIter;
    private NewIterator _sellerIterator;

    //endregion

    //region SINGLETON SETUP
    private static BuyerClerk ourInstance = new BuyerClerk();

    public static BuyerClerk getInstance() {
        return ourInstance;
    }

    private BuyerClerk() {
        super();
        _currentInventoryIter = null;
        _sellerIterator = null;
    }
    //endregion


    /**
     * Adds a product to the shopping cart.
     * @param item a Product to be added to the shopping cart.
     */
    public void addToCart(Product item){
        _shoppingCart.addItem(item);

    }

    /**
     * Function used to add a product to cart given the product id and seller id
     * @param productID
     * @param SellerID
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
        //todo: send toast to UI to let user know item was added to cart

    }

    public void removeFromCart(Product item){
        _shoppingCart.removeItem(item);

    }

    public void showShoppingCart(){
        Intent i = new Intent(BrowseList.getAppContext(), ShoppingCart.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BrowseList.getAppContext().startActivity(i);
    }

    public void updateCartCount(Product item, int quantity){
        _shoppingCart.updateCount(item, quantity);
    }

    public int getItemCount(Product item) {
        return _shoppingCart.itemCount(item);
    }

    public void finalCheckout(){
        Intent i = new Intent(Payment.getAppContext(), Checkout.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Payment.getAppContext().startActivity(i);

    }

    public ArrayList<String> getCreditInfo() {
        ArrayList<String> accountNumbers = new ArrayList<String>();
        ArrayList<CreditCard> cCards = StoreClerk.getInstance()._userAccountB.getcCards();
        for(int i = 0; i < cCards.size(); i++) {
            accountNumbers.add(cCards.get(i).getAccNumber());
        }
        return accountNumbers;
    }

    public void addNewCredit(String accNum, String expiration){
        StoreClerk.getInstance()._userAccountB.addcCard(accNum, expiration);
    }

    public void getVerifyPurchase(){
        Intent i = new Intent(ShoppingCart.getAppContext(), Payment.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ShoppingCart.getAppContext().startActivity(i);

    }

    //Used for checking out I replaced billBuyer since were faking the transaction. There
    //is no need to actually bill the buyer 4/17/15
    public void paySeller(){
        while(!_shoppingCart.isEmpty()) {
            Product prod = _shoppingCart.getFirstProd();
            int count = _shoppingCart.getFirstCount();

            AccountList accList = StoreClerk.getInstance()._accList;

            accList.set_isLookingForSeller(true);

            for (Iterator iter = _accList.iterator(); iter.hasNext(); ) {
                SellerAccount seller = (SellerAccount) iter.next();

                if (prod.get_SellerID().equals(seller.get_sellerID())) {
                    seller.set_revenues(seller.get_revenues() + (prod.get_sellingP() * count));
                    prod.set_quantity(prod.get_quantity() - count);
                }
            }
        }

    }

    public void cancelOrder(){

    }

    public int getCartCount(){
        return _shoppingCart.getCartQuantity();
    }

    public String getReceipt() {
        return _shoppingCart.printReceipt();
    }


    //Added this for checkout. Since we need to access the BrowseList from
    //other activities other than login, something like this makes sense in
    //StoreClerk. I believe. Also, i think the other methods to open
    //other Activities should be set up this way as well so the methods aren't
    //limited to a specific activity starting a new one. Added the
    //addFlag line on 4/18/15
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
     *
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


    public void setUpUser(User user, BuyerAccount buyer) {
        super._user = user;
        super._userAccountB = buyer;
    }
}
