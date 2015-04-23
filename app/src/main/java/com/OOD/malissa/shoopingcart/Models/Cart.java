package com.OOD.malissa.shoopingcart.Models;

import com.OOD.malissa.shoopingcart.Activities.Interfaces.CartObserver;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart {

    ArrayList<Product> selectedItems;
    ArrayList<Integer> itemCounts;
    CartObserver browseList;
    // decimal format used to properly format the doubles
    private DecimalFormat df = new DecimalFormat("0.00");

    /**
     * A Constructor for the cart that creates a new ArrayList of Products and Integers
     */
    public Cart(){
        selectedItems = new ArrayList<Product>();
        itemCounts = new ArrayList<Integer>();
    }

    /**
     * Sets the CartObserver for the Cart.
     * @param cartOb a CartObserver to be put in browseList
     * @author Malissa Augustin
     */
    public void setObserver(CartObserver cartOb)
    {
        browseList = cartOb;
    }


    /**
     * Adds an product to the cart. If the product is already in the cart, just add one to
     * its count.
     * @param item product to be added to the cart.
     * @author Paul Benedict Reyes
     */
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
     * @param item the product to be removed
     * @author Paul Benedict Reyes
     */
    public void removeItem(Product item){
        browseList.update(-(itemCounts.get(selectedItems.indexOf(item))),false);
        itemCounts.remove(selectedItems.indexOf(item));
        selectedItems.remove(item);

    }

    /**
     * Updates the count of a product in the cart with the given count.
     * @param item Product whose count will be changed.
     * @param count the new count of the Product
     * @author Malissa Augustin
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
     * @author Paul Benedict Reyes
     */
    public Product getCartItems(int index){
     return selectedItems.get(index);
    }

    /**
     * Obtains the number of unique Products in the cart.
     * @return the number of unique Products in the cart.
     * @author Paul Benedict Reyes
     */
    public int getCartQuantity(){

        return selectedItems.size();
    }

    /**
     * Obtains the number of occurrences of a Product in the Cart.
     * @param item the Product whose count will be retrieved.
     * @return the number of occurrences of the given Product.
     * @author Paul Benedict Reyes
     */
    public int itemCount(Product item) {
        return itemCounts.get(selectedItems.indexOf(item));

    }

    /**
     * A method that creates an ArrayList of String for the receipt to show in Checkout.
     * @return an ArrayList of String that is the receipt to display for checkout.
     * @author Paul Benedict Reyes
     */
    public ArrayList<String> printReceipt() {
        String receiptNames = "";
        String receiptPrices = "";
        float total = 0;

        for(int i = 0; i < selectedItems.size(); i++){
            receiptNames += selectedItems.get(i).get_name() + "\t\t\t\t\t\t\n";
            receiptPrices += "$" + df.format(selectedItems.get(i).get_sellingP()) + " x " + itemCounts.get(i).toString();
            receiptPrices += " = " + "$" + df.format((selectedItems.get(i).get_sellingP()*itemCounts.get(i))) + "\n";

            total += selectedItems.get(i).get_sellingP()*itemCounts.get(i);
        }

        receiptPrices += "\nTotal = " + "$" + df.format(total);

        ArrayList<String> receipt = new ArrayList<>();
        receipt.add(receiptNames);
        receipt.add(receiptPrices);

        return receipt;
    }

    /**
     * Obtain and remove the first Product in the cart.
     * @return the first product from the cart.
     * @author Paul Benedict Reyes
     */
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
     * @author Paul Benedict Reyes
     */
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
     * @author Paul Benedict Reyes
     */
    public boolean isEmpty(){
        return (selectedItems.isEmpty() && itemCounts.isEmpty());
    }

    /**
     * Obtains the count of the items in the cart.
     * @return an int that is the amount of items in the cart
     * @author Malissa Augustin
     */
    public int getCount() {
        int total = 0;
        for(int num : itemCounts )
        {
            total += num;
        }
        return total;
    }
}
