package com.OOD.malissa.shoopingcart.Activities.Interfaces;

/**
 * Created by Malissa on 4/19/2015.
 * updates observers with the count of the cart
 */
public interface CartObserver {
    public void update(int count, boolean isEmpty);
}
