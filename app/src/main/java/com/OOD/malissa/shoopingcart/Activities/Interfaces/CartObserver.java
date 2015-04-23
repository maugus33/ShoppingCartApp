package com.OOD.malissa.shoopingcart.Activities.Interfaces;

/**
 * updates observers with the count of the cart
 */
public interface CartObserver {
    public void update(int count, boolean isEmpty);
}
