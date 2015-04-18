package com.OOD.malissa.shoopingcart.Controllers;

import android.content.Context;
import android.content.Intent;

import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Activities.Checkout;
import com.OOD.malissa.shoopingcart.Activities.Login;
import com.OOD.malissa.shoopingcart.Activities.Payment;
import com.OOD.malissa.shoopingcart.Activities.ShoppingCart;
import com.OOD.malissa.shoopingcart.Models.AccountList;
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


    public void addToCart(Product item){
        _shoppingCart.addItem(item);

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
    //limited to a specific activity starting a new one.
    public void openBrowseList(Context context) {
        Intent i = new Intent(context, BrowseList.class);
        i.putExtra("User", StoreClerk.getInstance()._user);
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
                return null;
            }

        }

    }
}
