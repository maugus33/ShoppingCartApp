package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.Interfaces.CartObserver;

import java.util.ArrayList;

/**
 * Created by Malissa on 3/29/2015.
 */
public class Cart {

    ArrayList<Product> selectedItems;
    ArrayList<Integer> itemCounts;
    CartObserver browseList;

    public Cart(){
        selectedItems = new ArrayList<Product>();
        itemCounts = new ArrayList<Integer>();
    }

    public void setObserver(CartObserver cartOb)
    {
        browseList = cartOb;
    }


    /**
     * Adds an product to the cart. If the product is already in the cart, just add one to
     * its count.
     * @param item product to be added to the cart.
     */
    //Fixed bug where it would exceed the quantity of the product 4/15/15
    public void addItem(Product item){
        if(selectedItems.contains(item)){
            int index = selectedItems.indexOf(item);
            if(itemCounts.get(index) < selectedItems.get(index).get_quantity()) {
            itemCounts.set(index, (itemCounts.get(index)+1));
                browseList.update(1,false);
            }
            else
            {browseList.update(0,true);}

            return;
        }
        selectedItems.add(item);
        itemCounts.add(1);
        browseList.update(1,false);

    }

    /**
     * Removes a given product from the shopping cart.
     * @param item the product to be removed.
     */
    public void removeItem(Product item){
        itemCounts.remove(selectedItems.indexOf(item));
        selectedItems.remove(item);

    }

    /**
     * Updates the count of a product in the cart with the given count.
     * @param item Product whose count will be changed.
     * @param count the new count of the Product
     */
    public void updateCount(Product item, Integer count){
        // get cart count
        Integer cartCount =itemCounts.get(selectedItems.indexOf(item));
        itemCounts.set(selectedItems.indexOf(item), count);
        // update cart count on browselist
        browseList.update(count - cartCount, false);

    }

    /**
     * Gets a specified Product from the cart.
     * @param index the index of the Product to be retrieved
     * @return a Product at the given index.
     */
    public Product getCartItems(int index){
     return selectedItems.get(index);
    }

    /**
     * Obtains the number of unique Products in the cart.
     * @return the number of unique Products in the cart.
     */
    //Changed this to something bad a few days ago. I returned it to what it is
    //today 4/18/15
    public int getCartQuantity(){

        return selectedItems.size();
    }

    /**
     * Obtains the number of occurrences of a Product in the Cart.
     * @param item the Product whose count will be retrieved.
     * @return the number of occurrences of the given Product.
     */
    public int itemCount(Product item) {
        return itemCounts.get(selectedItems.indexOf(item));

    }

    /**
     * A crude Print Receipt that will be changed.
     * @return a String that is the receipt to display for checkout.
     */
    //TODO: Modify code to return ArrayList<String> to work with Table Layout for Checkout.
    //Added print receipt method for checkout. 4/17/15
    public String printReceipt() {
        String receipt = "";
        float total = 0;

        for(int i = 0; i < selectedItems.size(); i++){
            receipt += selectedItems.get(i).toString();
            receipt += " x " + itemCounts.get(i).toString();
            receipt += " = " + (selectedItems.get(i).get_sellingP()*itemCounts.get(i));
            receipt += "\n";
            total += selectedItems.get(i).get_sellingP()*itemCounts.get(i);
        }

        receipt += "\t\t\t\t\t" + "Total" + total;

        return receipt;
    }

    /**
     * Obtain and remove the first Product in the cart.
     * @return the first product from the cart.
     */
    //TODO: Combine getFirstProd and getFirstCount to return both in one call.
    //Get first and remove. Used for checkout 4/17/15
    public Product getFirstProd(){

        if(selectedItems.size() > 0) {
            Product first = selectedItems.get(0);
            selectedItems.remove(0);
            return first;
        }

        return null;
    }

    /**
     * Obtain and remove the count of the first product in the cart.
     * @return the count of the first product from the cart.
     */
    //TODO: Combine getFirstProd and getFirstCount to return both in one call.
    //Get first and remove. Used for checkout 4/17/15
    public int getFirstCount(){

        if(itemCounts.size() > 0) {
            int first = itemCounts.get(0);
            itemCounts.remove(0);
            return first;
        }

        return 0;
    }

    /**
     * A method that checks if the cart is empty.
     * @return a boolean that determines if the count is empty.
     */
    ///Check if cart is empty for checkout 4/17/15
    public boolean isEmpty(){
        return (selectedItems.isEmpty() && itemCounts.isEmpty());
    }

}
