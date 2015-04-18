package com.OOD.malissa.shoopingcart.Models;

import java.util.ArrayList;

/**
 * Created by Malissa on 3/29/2015.
 */
public class Cart {

    ArrayList<Product> selectedItems;
    ArrayList<Integer> itemCounts;

    public Cart(){
        selectedItems = new ArrayList<Product>();
        itemCounts = new ArrayList<Integer>();
    }

    //Fixed bug where it would exceed the quantity of the product 4/15/15
    public void addItem(Product item){
        if(selectedItems.contains(item)){
            int index = selectedItems.indexOf(item);
            if(itemCounts.get(index) < selectedItems.get(index).get_quantity()) {
            itemCounts.set(index, (itemCounts.get(index)+1));
            }
            return;
        }
        selectedItems.add(item);
        itemCounts.add(1);

    }

    public void removeItem(Product item){
        itemCounts.remove(selectedItems.indexOf(item));
        selectedItems.remove(item);

    }

    public void updateCount(Product item, Integer count){
        itemCounts.set(selectedItems.indexOf(item), count);

    }

    public Product getCartItems(int index){
     return selectedItems.get(index);
    }

    public int getCartQuantity(){

        int totalCount = 0;
        for (int i = 0; i < itemCounts.size(); i++) {
            totalCount = totalCount + itemCounts.get(i);
        }
        return totalCount;
    }

    public int itemCount(Product item) {
        return itemCounts.get(selectedItems.indexOf(item));

    }

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

    //Get first and remove. Used for checkout 4/17/15
    public Product getFirstProd(){

        if(selectedItems.size() > 0) {
            Product first = selectedItems.get(0);
            selectedItems.remove(0);
            return first;
        }

        return null;
    }

    //Get first and remove. Used for checkout 4/17/15
    public int getFirstCount(){

        if(itemCounts.size() > 0) {
            int first = itemCounts.get(0);
            itemCounts.remove(0);
            return first;
        }

        return 0;
    }

    ///Check if cart is empty for checkout 4/17/15
    public boolean isEmpty(){
        return (selectedItems.isEmpty() && itemCounts.isEmpty());
    }

}
