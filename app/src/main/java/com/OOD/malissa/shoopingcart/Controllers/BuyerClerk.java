package com.OOD.malissa.shoopingcart.Controllers;

import com.OOD.malissa.shoopingcart.Models.Cart;
import com.OOD.malissa.shoopingcart.Models.Product;

import java.util.ArrayList;

/**
 * Created by Malissa on 3/29/2015.
 */
public class BuyerClerk extends StoreClerk {

    //region SINGLETON SETUP
    private static BuyerClerk ourInstance = new BuyerClerk();

    public static BuyerClerk getInstance() {
        return ourInstance;
    }

    private BuyerClerk() {
        super();
    }
    //endregion

    //region INSTANCE VARIABLE
    private Cart _shoppingCart;
    private int currentInventoryIndex;
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

    }


    public Product getAStoreProd(){
        return null;
    }
}
