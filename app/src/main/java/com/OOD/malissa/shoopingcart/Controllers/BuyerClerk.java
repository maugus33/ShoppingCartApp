package com.OOD.malissa.shoopingcart.Controllers;

import com.OOD.malissa.shoopingcart.Models.Cart;
import com.OOD.malissa.shoopingcart.Models.Interfaces.NewIterator;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.Models.SellerAccount;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Malissa on 3/29/2015.
 */
public class BuyerClerk extends StoreClerk {


    //region INSTANCE VARIABLE
    private Cart _shoppingCart;
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

    }

    public void removeFromCart(Product item){

    }

    public void showShoppingCart(){

    }

    public void updateCartCount(Product item, int quantity){

    }

    public void finalCheckout(){

    }

    public ArrayList<String> getCreditInfo(){
        return null;
    }

    public void addNewCredit(){

    }

    public void getVerifyPurchase(){

    }

    private void chargeBuyer(double bill){

    }

    public void cancelOrder(){

    }

    public int getCartCount(){
        return 0;
    }

    /**
     * Function used to get the current items in the cart
     * @return a copy of what's in the model
     */
    public ArrayList<Product> getCartItems(){
        return null;
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
        // setup an interator for this new inventory
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
