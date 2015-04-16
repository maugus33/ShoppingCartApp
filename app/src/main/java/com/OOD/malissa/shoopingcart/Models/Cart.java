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

        return selectedItems.size();
    }

    public int itemCount(Product item) {
        return itemCounts.get(selectedItems.indexOf(item));

    }

    public boolean hasItem(Product other){
        return selectedItems.contains(other);
    }

}
